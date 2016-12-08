package so.sao.analytics.sdk.work.kafka;

import so.sao.analytics.sdk.kafka.logger.UserBasicInfoLogger;

import java.util.Map;

/**
 * User basic info kafkaProducer
 *
 * @author senhui.li
 */
public class UserBasicInfoProducer extends EventBasicProducer {

    public UserBasicInfoProducer(String topicName, UserBasicInfoLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
