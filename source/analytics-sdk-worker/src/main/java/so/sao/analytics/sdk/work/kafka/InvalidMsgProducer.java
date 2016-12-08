package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.InvalidMsgLogger;

/**
 * Send all invalid message to app system topic
 *
 * @author senhui.li
 */
public class InvalidMsgProducer extends EventBasicProducer {

    public InvalidMsgProducer(String topicName, InvalidMsgLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
