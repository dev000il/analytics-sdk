package so.sao.analytics.sdk.work.kinesis.kpl;


import so.sao.analytics.sdk.kafka.logger.MgmtActionLogger;

/**
 * Management Action kafkaProducer
 *
 * @author senhui.li
 */
public class MgmtActionProducer extends EventBasicProducer {

    public MgmtActionProducer(String streamName, MgmtActionLogger logger, String partitionKey, Boolean running) {
        super(streamName, logger, partitionKey, running);
    }
}
