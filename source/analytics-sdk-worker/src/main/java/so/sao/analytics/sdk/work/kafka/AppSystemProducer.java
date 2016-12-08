package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.AppSystemLogger;

/**
 * Application System kafkaProducer
 *
 * @author senhui.li
 */
public class AppSystemProducer extends EventBasicProducer {

    public AppSystemProducer(String topicName, AppSystemLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
