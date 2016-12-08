package so.sao.analytics.sdk.work.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.util.ConfigReader;
import so.sao.analytics.sdk.work.ProducerInitialization;

/**
 * Kafka kafkaProducer initialization
 *
 * @author senhui.li
 */
public class KafkaProducerInitialization extends ProducerInitialization {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerInitialization.class);

    public static KafkaProducerInitialization create() {
        return create(ConfigReader.DEF_CONFIG_PATH, null, Boolean.TRUE);
    }

    public static KafkaProducerInitialization create(Boolean isRunning) {
        return create(ConfigReader.DEF_CONFIG_PATH, null, isRunning);
    }

    public static KafkaProducerInitialization create(Map<?, ?> consumerProp) {
        return create(ConfigReader.DEF_CONFIG_PATH, consumerProp, Boolean.TRUE);
    }

    public static KafkaProducerInitialization create(Map<?, ?> consumerProp, Boolean running) {
        return create(ConfigReader.DEF_CONFIG_PATH, consumerProp, running);
    }

    public static KafkaProducerInitialization create(String configFile, Map<?, ?> consumerProp, Boolean running) {
        return new KafkaProducerInitialization(configFile, consumerProp, running);
    }

    public KafkaProducerInitialization(String configFile, Map<?, ?> customProp, Boolean running) {
        super(configFile, customProp, running);
        logger.info("Kafka producers init.");

        this.activityProducer = new ActivityProducer(configReader.getActivityTopicName(),
                activityLogger, consumerProp, this.running);
        this.transactionProducer = new TransactionProducer(configReader.getTransactionTopicName(),
                transactionLogger, consumerProp, this.running);
        this.thirdPartyProducer = new ThirdPartyProducer(configReader.getThirdPartyTopicName(),
                thirdPartyLogger, consumerProp, this.running);
        this.appSystemProducer = new AppSystemProducer(configReader.getAppSystemTopicName(),
                appSystemLogger, consumerProp, this.running);
        this.mgmtActionProducer = new MgmtActionProducer(configReader.getMgmtActionTopicName(),
                mgmtActionLogger, consumerProp, this.running);
        this.openAPIProducer = new OpenAPILogProducer(configReader.getOpenAPILogTopicName(),
                openAPILogLogger, consumerProp, this.running);
        this.userBasicInfoProducer = new UserBasicInfoProducer(configReader.getUserInfoTopicName(),
                userBasicInfoLogger, consumerProp, this.running);
        this.invalidMsgProducer = new InvalidMsgProducer(configReader.getInvalidMsgTopicName(),
                invalidMsgLogger, consumerProp, this.running);

        startInvalidMsgProducer();
    }

}
