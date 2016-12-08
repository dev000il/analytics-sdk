package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.ThirdpartyLogger;

/**
 * Thirdparty kinesis kinsisAPIProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class ThirdPartyProducer extends EventBasicProducer {

    public ThirdPartyProducer(String streamName, ThirdpartyLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
