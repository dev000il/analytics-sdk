package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.AppSystemLogger;

/**
 * Application System kafkaProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class AppSystemProducer extends EventBasicProducer {

    public AppSystemProducer(String streamName, AppSystemLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
