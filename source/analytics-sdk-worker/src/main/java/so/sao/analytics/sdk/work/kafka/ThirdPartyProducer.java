package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.ThirdpartyLogger;

/**
 * Thirdparty kafka kafkaProducer
 *
 * @author senhui.li
 */
public class ThirdPartyProducer extends EventBasicProducer {

    public ThirdPartyProducer(String topicName, ThirdpartyLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
