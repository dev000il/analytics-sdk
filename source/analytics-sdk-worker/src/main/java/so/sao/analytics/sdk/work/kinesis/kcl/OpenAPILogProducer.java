package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.OpenAPILogLogger;

/**
 *  Open API Log kinesisAPIProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class OpenAPILogProducer extends EventBasicProducer {

    public OpenAPILogProducer(String streamName, OpenAPILogLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
