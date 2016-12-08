package so.sao.analytics.sdk.work.kinesis.kcl;

import so.sao.analytics.sdk.kafka.logger.UserBasicInfoLogger;


/**
 * Transaction kinesis kinesisAPIProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class UserBasicInfoProducer extends EventBasicProducer {

    public UserBasicInfoProducer(String streamName, UserBasicInfoLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
