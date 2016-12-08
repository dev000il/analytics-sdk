package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.AppSystemLogger;

/**
 * Application System kafkaProducer
 *
 * @author senhui.li
 */
public class AppSystemProducer extends EventBasicProducer {

    public AppSystemProducer(String streamName, AppSystemLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
