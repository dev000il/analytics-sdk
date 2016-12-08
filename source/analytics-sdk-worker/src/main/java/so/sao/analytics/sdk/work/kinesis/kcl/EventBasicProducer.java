package so.sao.analytics.sdk.work.kinesis.kcl;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.DescribeStreamResult;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;

import so.sao.analytics.sdk.kafka.logger.EventBasicLogger;
import so.sao.analytics.sdk.kinesis.AWSKinesisClient;

/**
 *
 * @Description : Kinesis kinesisProducer super class (A single thread worker)
 * @author chengjian.liang,senhui.li
 * @2016年10月11日
 */
public class EventBasicProducer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(EventBasicProducer.class);

    protected EventBasicLogger eventLogger;

    protected List <PutRecordsRequestEntry> events  = new ArrayList<>();

    protected AmazonKinesis kinesisClient;

    protected String partitionKey;

    protected PutRecordsRequest putRecordsRequest;

    protected String streamName;

    protected boolean kinesisRunning;

    // maxSize = 0 means kafkaProducer will process all events
    protected int maxSize = 500;


    public EventBasicProducer(String streamName, EventBasicLogger eventLogger, String  partitionKey, Boolean running) {

        if (eventLogger == null) {
            throw new RuntimeException("There require event logger object before init Kinesis producer.");
        }

        kinesisClient = AWSKinesisClient.getAmazonKinesisClient();

        // Validate that the streamName exists and is active
        validateStream(kinesisClient, streamName);

        this.putRecordsRequest = AWSKinesisClient.getPutRecordsRequest();
        if(this.putRecordsRequest == null){
            logger.error("Create Kinesis producer put records request failed.");
        }

        this.streamName = streamName;
        this.eventLogger = eventLogger;
        this.partitionKey = partitionKey;
        this.kinesisRunning = running.booleanValue();
    }

    @Override
    public void run() {

        // 提取数据
        extractEvents();

        // 判断集合不为空就发送数据
        if (!events.isEmpty()) {

            // 调用发送方法
            sendMessage();
        }
    }

    /**
     * Extract data from the queue
     */
    protected void extractEvents() {

        // 创建一个最大容量为500 的list集合
        List<byte[]> tmp = this.eventLogger.pull(this.maxSize);
        // 如果tmp临时集合不为空
        if (tmp != null && !tmp.isEmpty()) {
            try {
                // 遍历tmp中的数据
                for (byte[] message : tmp) {
                    PutRecordsRequestEntry putRecordsRequestEntry = AWSKinesisClient
                            .getPutRecordsRequestEntry(ByteBuffer.wrap(message), this.partitionKey);
                    this.events.add(putRecordsRequestEntry);
                }
            } catch (Exception e) {
                // TODO how to do it when extract events from MapDB failure
                logger.error(streamName+" - Fetch message store database failed.", e);
            }
        }
    }


    /**
     * Through the KinesisClient send events to Kinesis
     */
    protected  void sendMessage() {

        if (!this.kinesisRunning) {
            logger.info("{} - Disabled Kinesis[KCL] Push for DEV & TEST profile.", streamName);
            this.events.clear();
            return;
        }

        if (this.events != null && !this.events.isEmpty()) {
            try {

                AWSKinesisClient.sendKinesisPutRecords(kinesisClient, putRecordsRequest, events, streamName);
                this.events.clear();
            } catch (Exception e) {
                logger.error(streamName + " - Send data to kafka brokers failed.", e);
            }
        }
    }


    /**
     * Checks if the streamName exists and is active
     *
     * @param kinesisClient Amazon Kinesis client instance
     * @param streamName Name of streamName
     */
    private static void validateStream(AmazonKinesis kinesisClient, String streamName) {

        try {
            DescribeStreamResult result = kinesisClient.describeStream(streamName);

            if(!"ACTIVE".equals(result.getStreamDescription().getStreamStatus())) {
                logger.error("Stream " + streamName + " is not active. Please wait a few moments and try again.");
            }

        }  catch (Exception e) {
            logger.error("Error found while describing the streamName " + streamName);
        }
    }
}
