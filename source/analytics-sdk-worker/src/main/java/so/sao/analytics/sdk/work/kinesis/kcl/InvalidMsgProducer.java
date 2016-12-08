package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.InvalidMsgLogger;

/**
 * Send all invalid message to app system topic
 *
 * 
 * @author chengjian.liang
 *
 */
public class InvalidMsgProducer extends EventBasicProducer {

    public InvalidMsgProducer(String streamName, InvalidMsgLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
