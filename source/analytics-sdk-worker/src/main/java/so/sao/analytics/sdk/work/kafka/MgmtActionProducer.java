package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import so.sao.analytics.sdk.kafka.logger.MgmtActionLogger;

/**
 * Management Action kafkaProducer
 *
 * @author senhui.li
 */
public class MgmtActionProducer extends EventBasicProducer {

    public MgmtActionProducer(String topicName, MgmtActionLogger logger, Map<?, ?> consumerProp, Boolean isRunning) {
        super(topicName, logger, consumerProp, isRunning);
    }
}
