package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.TransactionLogger;

/**
 * Transaction kafka kafkaProducer
 *
 * @author senhui.li
 */
public class TransactionProducer extends EventBasicProducer {

    public TransactionProducer(String streamName, TransactionLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
