package so.sao.analytics.sdk.work.kinesis.kpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.util.ConfigReader;
import so.sao.analytics.sdk.work.ProducerInitialization;

import java.util.Map;

/**
 * AWS Kinesis producer lib type producers initialization
 *
 * @author senhui.li
 */
public class KinesisKPLProducerInitialization extends ProducerInitialization {

    private static final Logger logger = LoggerFactory.getLogger(KinesisKPLProducerInitialization.class);


    public static KinesisKPLProducerInitialization create() {
        return create(ConfigReader.AWS_DEF_CONFIG_PATH, null, Boolean.TRUE);
    }

    public static KinesisKPLProducerInitialization create(Boolean running) {
        return create(ConfigReader.AWS_DEF_CONFIG_PATH, null, running);
    }

    public static KinesisKPLProducerInitialization create(Map<?, ?> consumerProp, Boolean running) {
        return create(ConfigReader.AWS_DEF_CONFIG_PATH, consumerProp, running);
    }

    public static KinesisKPLProducerInitialization create(String configFile, Map<?, ?> consumerProp, Boolean running) {
        return new KinesisKPLProducerInitialization(configFile, consumerProp, running);
    }

    public KinesisKPLProducerInitialization(String configFile, Map<?, ?> consumerProp, Boolean running) {
        super(configFile, consumerProp, running);
        logger.info("AWS Kinesis producer[KPL] init.");

        this.activityProducer = new ActivityProducer(configReader.getActivityTopicName(),
                activityLogger, ACTIVITIESTIMESTAMP, this.running);
        this.transactionProducer = new TransactionProducer(configReader.getTransactionTopicName(),
                transactionLogger, TRANSACTIONTIMESTAMP, this.running);
        this.thirdPartyProducer = new ThirdPartyProducer(configReader.getThirdPartyTopicName(),
                thirdPartyLogger, THIRDPARTYTIMESTAMP, this.running);
        this.appSystemProducer = new AppSystemProducer(configReader.getAppSystemTopicName(),
                appSystemLogger, APPSYSTEMTIMESTAMP, this.running);
        this.mgmtActionProducer = new MgmtActionProducer(configReader.getMgmtActionTopicName(),
                mgmtActionLogger, MGMTACTIONTIMESTAMP, this.running);
        this.openAPIProducer = new OpenAPILogProducer(configReader.getOpenAPILogTopicName(),
                openAPILogLogger, OPENAPILOGTIMESTAMP, this.running);
        this.userBasicInfoProducer = new UserBasicInfoProducer(configReader.getUserInfoTopicName(),
                userBasicInfoLogger, USERBASICINFOTIMESTAMP, this.running);
        this.invalidMsgProducer = new InvalidMsgProducer(configReader.getInvalidMsgTopicName(),
                invalidMsgLogger, INVALIDMSGTIMESTAMP, this.running);

        startInvalidMsgProducer();
    }
}
