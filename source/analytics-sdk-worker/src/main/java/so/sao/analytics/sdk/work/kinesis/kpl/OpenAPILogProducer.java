package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.OpenAPILogLogger;

/**
 * Open API Log kafkaProducer
 *
 * @author senhui.li
 */
public class OpenAPILogProducer extends EventBasicProducer {

    public OpenAPILogProducer(String streamName, OpenAPILogLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
