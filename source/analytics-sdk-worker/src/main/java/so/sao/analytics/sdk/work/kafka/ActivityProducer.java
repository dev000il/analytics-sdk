package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.ActivityLogger;

/**
 * Activity kafkaProducer
 *
 * @author senhui.li
 */
public class ActivityProducer extends EventBasicProducer {

    public ActivityProducer(String topicName, ActivityLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
