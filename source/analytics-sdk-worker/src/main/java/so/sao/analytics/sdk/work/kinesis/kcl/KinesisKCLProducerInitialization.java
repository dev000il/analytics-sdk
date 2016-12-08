package so.sao.analytics.sdk.work.kinesis.kcl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.util.ConfigReader;
import so.sao.analytics.sdk.work.ProducerInitialization;

import java.util.Map;

/**
 * kinesisAPI kinesisAPIProducer initialization
 *
 * 
 * @author chengjian.liang
 *
 */
public class KinesisKCLProducerInitialization extends ProducerInitialization {

    private static final Logger logger = LoggerFactory.getLogger(KinesisKCLProducerInitialization.class);

    public static KinesisKCLProducerInitialization create() {
    	return create(ConfigReader.AWS_DEF_CONFIG_PATH, null, Boolean.TRUE);
    }

    public static KinesisKCLProducerInitialization create(Boolean running) {
    	return create(ConfigReader.AWS_DEF_CONFIG_PATH, null, running);
    }

    public static KinesisKCLProducerInitialization create(String configPath, Map<?, ?> consumerProp, Boolean running) {
    	return new KinesisKCLProducerInitialization(configPath, consumerProp, running);
    }

    public KinesisKCLProducerInitialization(String configPath, Map<?, ?> consumerProp, Boolean running) {
        super(configPath, consumerProp, running);
        logger.info("Kinesis producers[KCL] init.");



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
