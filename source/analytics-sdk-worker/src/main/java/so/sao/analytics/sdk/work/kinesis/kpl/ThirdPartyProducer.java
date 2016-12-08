package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.ThirdpartyLogger;

/**
 * Third party kafka kafkaProducer
 *
 * @author senhui.li
 */
public class ThirdPartyProducer extends EventBasicProducer {

    public ThirdPartyProducer(String streamName, ThirdpartyLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
