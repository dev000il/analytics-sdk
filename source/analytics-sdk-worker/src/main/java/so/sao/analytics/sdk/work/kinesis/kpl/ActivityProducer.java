package so.sao.analytics.sdk.work.kinesis.kpl;

import so.sao.analytics.sdk.kafka.logger.ActivityLogger;


public class ActivityProducer extends EventBasicProducer {

    public ActivityProducer(String streamName, ActivityLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
