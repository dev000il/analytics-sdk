package so.sao.analytics.sdk.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.work.kafka.KafkaProducerInitialization;
import so.sao.analytics.sdk.work.kinesis.kcl.KinesisKCLProducerInitialization;
import so.sao.analytics.sdk.work.kinesis.kpl.KinesisKPLProducerInitialization;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This parent class for Producer listener
 *
 * @author senhui.li
 */
public abstract class ProducerServletInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ProducerServletInitListener.class);

    // Use host name [test-broker1.sao.so:9092] for default value
    private static final String KAFKA_TEST_BROKER_LIST = "test-broker1.sao.so:9092";

    protected ProducerInitialization producerInit;
    protected Properties prop = new Properties();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        if (producerInit != null) {
            logger.info("Servlet container destroy working, ready close producer...");
            producerInit.close();
        }
    }

    /**
     * Set the some Kinesis server related config
     *
     * @param customerConf
     *            <p>
     *            about the environment and events settings the key list:
     *            </p>
     *            <p>
     *            Kinesis.Producer.Type: PKL/KCL
     *            </p>
     *            <p>
     *            Activity.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            AppSystem.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            MgmAction.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            Transaction.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            OpenAPILog.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            UserBasicInfo.Topic.Enable: true/false
     *            </p>
     *
     */
    public void initKinesisProducer(Map<Object, Object> customerConf) {

        this.prop.putAll(customerConf);

        String type = this.prop.getProperty("Kinesis.Producer.Type", "KCL");
        logger.info("Kinesis push type: {}", type);
        if ("KPL".equalsIgnoreCase(type)) {
            this.producerInit = KinesisKPLProducerInitialization.create();
        } else {
            this.producerInit = KinesisKCLProducerInitialization.create();
        }

        initProducers();
    }

    /**
     * Set the some Kafka server related config
     *
     * @param customerConf
     *            <p>
     *            about the environment and events settings the key list:
     *            </p>
     *            <p>
     *            EnvironmentProfile: PROD, STABLE, TEST
     *            </p>
     *            <p>
     *            Kafka.Broker.List: test-broker1.sao.so:9092 OR not set
     *            </p>
     *            <p>
     *            Activity.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            AppSystem.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            MgmAction.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            Transaction.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            OpenAPILog.Topic.Enable: true/false
     *            </p>
     *            <p>
     *            UserBasicInfo.Topic.Enable: true/false
     *            </p>
     *
     */
    public void initKafkaProduer(Map<Object, Object> customerConf) {

        this.prop.putAll(customerConf);

        String env = prop.getProperty("EnvironmentProfile", "TEST");
        logger.info("Platform current environment: {}", env);
        if (env != null && !env.equalsIgnoreCase("PROD")) {
            String brokers = prop.getProperty("Kafka.Broker.List", KAFKA_TEST_BROKER_LIST);
            Map<String, String> server = new HashMap<>();
            server.put("metadata.broker.list", brokers);
            logger.info("Kafka producer server list: {}", server);
            this.producerInit = KafkaProducerInitialization.create(server);
        } else {
            this.producerInit = KafkaProducerInitialization.create();
        }

        initProducers();
    }

    protected void initProducers() {

        logger.info("Producer init starting...");

        if (this.producerInit != null) {
            startProducers();

            // This hook can work when the container close not correct
            Runtime.getRuntime().addShutdownHook(new Thread(){

                @Override
                public void run() {
                    logger.info("JVM Hook working, ready close producer...");
                    producerInit.close();
                }

            });

        } else {
            throw new RuntimeException("Producer init failed when start servlet container.");
        }



        logger.info("Producer init end...");
    }

    protected void startProducers() {

        // Start Activity Event Producer
        if (getPropBooleanVal("Activity.Topic.Enable")){
            logger.info("activity topic enabled...");
            producerInit.startActivityProducer();
        }

        // Start Application System Event Producer
        if (getPropBooleanVal("AppSystem.Topic.Enable")){
            logger.info("app system topic enabled...");
            producerInit.startAppSystemProducer();
        }

        // Start Management Action Event Producer
        if (getPropBooleanVal("MgmAction.Topic.Enable")){
            logger.info("management action topic enabled...");
            producerInit.startMgmtActionProducer();
        }

        // Start Transaction Event Producer
        if (getPropBooleanVal("Transaction.Topic.Enable")){
            logger.info("transaction topic enabled...");
            producerInit.startTransactionProducer();
        }

        // Start Third Party Event Producer
        if (getPropBooleanVal("Thirdparty.Topic.Enable")){
            logger.info("third party topic enabled...");
            producerInit.startThirdPartyProducer();
        }

        // Start Third Party Event Producer
        if (getPropBooleanVal("UserBasicInfo.Topic.Enable")) {
            logger.info("user basic info topic enabled...");
            producerInit.startUserBasicInfoProducer();
        }

        // Start Open API Log Event Producer
        if (getPropBooleanVal("OpenAPILog.Topic.Enable")){
            logger.info("open API log topic enabled...");
            producerInit.startOpenAPILogProducer();
        }
    }

    private boolean getPropBooleanVal(String key) {
        String tmp = prop.getProperty(key, "false");
        return Boolean.valueOf(tmp).booleanValue();
    }
}
