package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.OpenAPILogLogger;

/**
 * Open API Log kafkaProducer
 *
 * @author senhui.li
 */
public class OpenAPILogProducer extends EventBasicProducer {

    public OpenAPILogProducer(String topicName, OpenAPILogLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
