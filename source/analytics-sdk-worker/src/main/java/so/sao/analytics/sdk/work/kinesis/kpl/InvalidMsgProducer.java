package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.InvalidMsgLogger;

/**
 * Send all invalid message to app system topic
 *
 * @author senhui.li
 */
public class InvalidMsgProducer extends EventBasicProducer {

    public InvalidMsgProducer(String streamName, InvalidMsgLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
