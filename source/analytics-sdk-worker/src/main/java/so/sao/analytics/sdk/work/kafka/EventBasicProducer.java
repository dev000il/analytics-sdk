package so.sao.analytics.sdk.work.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import so.sao.analytics.sdk.kafka.logger.EventBasicLogger;

/**
 * Kafka kafkaProducer super class (A single thread worker)
 *
 * @author senhui.li
 */
public class EventBasicProducer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(EventBasicProducer.class);

    protected EventBasicLogger eventLogger;

    protected Producer<Integer, byte[]> kafkaProducer;

    protected List<KeyedMessage<Integer, byte[]>> events = new ArrayList<>();

    protected boolean kafkaRunning;

    protected String topic;

    // maxSize = 0 means kafkaProducer will process all events
    protected int maxSize = 500;

    public EventBasicProducer(String topicName, EventBasicLogger eventLogger, Map<?, ?> consumerProp, Boolean isRunning) {
        if (eventLogger == null) {
            throw new RuntimeException("There require event logger object before init Kafka producer.");
        }

        Properties producerProp = new Properties();
        try {
            InputStream is = KafkaProducerInitialization.class.getResourceAsStream("/kafka-config.properties");
            producerProp.load(is);
        } catch (IOException e) {
            logger.error("Load kafka configure file failed.", e);
            throw new RuntimeException(e);
        }

        // try to fixed the stable environment which were special send data to test data pipeline
        if (consumerProp != null && !consumerProp.isEmpty()) {
            producerProp.putAll(consumerProp);
        }

        ProducerConfig config = new ProducerConfig(producerProp);
        this.kafkaProducer = new Producer<>(config);

        this.kafkaRunning = isRunning.booleanValue();
        this.eventLogger = eventLogger;
        this.topic = topicName;
    }

    @Override
    public void run() {

        extractEvents();

        if (!events.isEmpty()) {
            sendMessage();
        }
    }

    protected void extractEvents() {

        List<byte[]> tmp = this.eventLogger.pull(this.maxSize);
        if (tmp != null && !tmp.isEmpty()) {
            try {
                this.events.addAll(tmp.stream().map(message -> new KeyedMessage<Integer, byte[]>(topic, message)).collect(Collectors.toList()));
            } catch (Exception e) {
                // TODO how to do it when extract events from MapDB failure
                logger.error(topic + " - Fetch message store database failed.", e);
            }
        }
    }

    protected void sendMessage() {

        if (!this.kafkaRunning) {
            logger.info("{} - Disabled Kafka Push for DEV & TEST profile.", topic);
            this.events.clear();
            return;
        }

        if (this.events != null && !this.events.isEmpty()) {
            try {
                kafkaProducer.send(this.events);
                this.events.clear();
            } catch (Exception e) {
                logger.error(topic + " - Send data to kafka brokers failed.", e);
            }
        }
    }

}
