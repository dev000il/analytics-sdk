package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.TransactionLogger;

/**
 * Transaction kafka kafkaProducer
 *
 * @author senhui.li
 */
public class TransactionProducer extends EventBasicProducer {

    public TransactionProducer(String topicName, TransactionLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
