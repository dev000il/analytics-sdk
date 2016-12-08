package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.TransactionLogger;

/**
 * Transaction kinesis kinesisAPIProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class TransactionProducer extends EventBasicProducer {

    public TransactionProducer(String streamName, TransactionLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
