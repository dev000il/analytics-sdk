package so.sao.analytics.sdk.work.kinesis.kcl;


import so.sao.analytics.sdk.kafka.logger.MgmtActionLogger;

/**
 * Management Action kinesisAPIProducer
 *
 * 
 * @author chengjian.liang
 *
 */
public class MgmtActionProducer extends EventBasicProducer {

    public MgmtActionProducer(String streamName, MgmtActionLogger logger, String partitionKey, Boolean running) {

        super(streamName, logger, partitionKey, running);
    }
}
