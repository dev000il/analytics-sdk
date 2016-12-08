package so.sao.analytics.sdk.work.kinesis.kpl;

import so.sao.analytics.sdk.kafka.logger.UserBasicInfoLogger;


/**
 * User basic info kafkaProducer
 *
 * @author senhui.li
 */
public class UserBasicInfoProducer extends EventBasicProducer {

    public UserBasicInfoProducer(String streamName, UserBasicInfoLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
