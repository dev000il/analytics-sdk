package so.sao.analytics.sdk.work.kinesis.kpl;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.analytics.sdk.util.ConfigReader;
import so.sao.analytics.sdk.kafka.logger.EventBasicLogger;
import so.sao.analytics.sdk.kinesis.CestbonCredentialsProvider;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Kafka kafkaProducer super class (A single thread worker)
 *
 * @author senhui.li
 */
public class EventBasicProducer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(EventBasicProducer.class);

    private static final Random RANDOM = new Random();

    protected EventBasicLogger eventLogger;

    protected List<byte[]> events = new ArrayList<>();

    protected String partitionKey;

    protected boolean kinesisRunning;

    protected String streamName;

    // maxSize = 0 means kafkaProducer will process all events
    protected int maxSize = 500;

    protected KinesisProducer kinesisProducer;

    public EventBasicProducer(String streamName, EventBasicLogger eventLogger, String partitionKey, Boolean running) {

        if (eventLogger == null) {
            throw new RuntimeException("There require event logger object before init AWS kinesis kpl producer.");
        }

        try {
            ConfigReader configReader = ConfigReader.createOrGetInstance(ConfigReader.AWS_DEF_CONFIG_PATH);
            KinesisProducerConfiguration kinesisPcf = new KinesisProducerConfiguration();
            kinesisPcf.setRegion(configReader.getAWSRegionName());
            kinesisPcf.setKinesisEndpoint(configReader.getKinesisEndPointName());
            kinesisPcf.setCloudwatchEndpoint(configReader.getKinesisCloudWatchEndPoint());

            kinesisPcf.setMaxConnections(configReader.getKinesisMaxConnect());
            kinesisPcf.setRequestTimeout(configReader.getKinesisRequestTimeout());
            kinesisPcf.setRecordMaxBufferedTime(configReader.getKinesisRecordsMaxBufferedTime());

            kinesisPcf.setCredentialsProvider(CestbonCredentialsProvider.getCredentialsProvider());
            this.kinesisProducer = new KinesisProducer(kinesisPcf);
        } catch (Exception e) {
            logger.error("Init Kinesis producer failed.", e);
        }

        this.streamName = streamName;
        this.eventLogger = eventLogger;
        this.partitionKey = partitionKey;
        this.kinesisRunning = running.booleanValue();

    }


    @Override
    public void run() {

        extractEvents();

        if (!this.events.isEmpty()) {
            sendMessage();
        }

    }

    protected void extractEvents() {

        List<byte[]> tmp = this.eventLogger.pull(this.maxSize);

        if (tmp != null && !tmp.isEmpty()) {
            try {
                this.events.addAll(tmp.stream().collect(Collectors.toList()));
                /*for (byte[] message : tmp) {
                    this.events.add(message);
                }*/
            } catch (Exception e) {
                // TODO how to do it when extract events from MapDB failure
                logger.error(streamName + " - Fetch message store database failed.", e);
            }
        }
    }

    protected void sendMessage() {

        if (!this.kinesisRunning) {
            logger.info("{} - Disabled Kinesis[KPL] Push for DEV & TEST profile.", streamName);
            this.events.clear();
            return;
        }

        if (this.events != null && !this.events.isEmpty()) {
            try {

                for(byte[] message : events){
                    kinesisProducer.addUserRecord(streamName, partitionKey, randomExplicitHashKey(), ByteBuffer.wrap(message));
                }

                this.events.clear();
            } catch (Exception e) {
                logger.error(streamName + " - Send data to Kinesis server with KPL type failed.", e);
            }
        }
    }

    protected String randomExplicitHashKey() {
        return new BigInteger(128, RANDOM).toString(10);
    }

}
