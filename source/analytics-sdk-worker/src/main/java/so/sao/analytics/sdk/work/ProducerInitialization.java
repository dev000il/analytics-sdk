package so.sao.analytics.sdk.work;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.analytics.sdk.kafka.logger.*;
import so.sao.analytics.sdk.util.ConfigReader;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Build a interface for ready adaptive different environment
 *
 * @author senhui.li
 */
public abstract class ProducerInitialization {

    private static final Logger logger = LoggerFactory.getLogger(ProducerInitialization.class);

    protected static final ScheduledExecutorService PRODUCER_EXECUTOR = Executors.newScheduledThreadPool(10);

    protected static final String ACTIVITIESTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String OPENAPILOGTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String MGMTACTIONTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String THIRDPARTYTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String TRANSACTIONTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String USERBASICINFOTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String APPSYSTEMTIMESTAMP = Long.toString(System.currentTimeMillis());

    protected static final String INVALIDMSGTIMESTAMP = Long.toString(System.currentTimeMillis());
    
    protected ConfigReader configReader;
    protected Map<?, ?> consumerProp;
    protected boolean running;

    protected ActivityLogger activityLogger;
    protected TransactionLogger transactionLogger;
    protected ThirdpartyLogger thirdPartyLogger;
    protected AppSystemLogger appSystemLogger;
    protected MgmtActionLogger mgmtActionLogger;
    protected OpenAPILogLogger openAPILogLogger;
    protected UserBasicInfoLogger userBasicInfoLogger;
    protected InvalidMsgLogger invalidMsgLogger;

    protected Runnable activityProducer;
    protected Runnable transactionProducer;
    protected Runnable thirdPartyProducer;
    protected Runnable appSystemProducer;
    protected Runnable mgmtActionProducer;
    protected Runnable openAPIProducer;
    protected Runnable userBasicInfoProducer;
    protected Runnable invalidMsgProducer;

    public ProducerInitialization(String configFile, Map<?, ?> consumerProp, Boolean running) {

        logger.info("Found config file: [{}]", configFile);
        logger.info("Found customer properties setting: {}", consumerProp);
        logger.info("Found push server running: {}", running);

        this.configReader = ConfigReader.createOrGetInstance(configFile);
        setConsumerProp(consumerProp);
        setRunning(running);

        this.activityLogger = ActivityLogger.create();
        this.appSystemLogger = AppSystemLogger.create();
        this.transactionLogger = TransactionLogger.create();
        this.thirdPartyLogger = ThirdpartyLogger.create();
        this.mgmtActionLogger = MgmtActionLogger.create();
        this.userBasicInfoLogger = UserBasicInfoLogger.create();
        this.openAPILogLogger = OpenAPILogLogger.create();
        this.invalidMsgLogger = InvalidMsgLogger.create();
    }


    public ConfigReader getConfigReader() {
        return configReader;
    }

    public void setConfigReader(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public Map<?, ?> getConsumerProp() {
        return consumerProp;
    }

    public void setConsumerProp(Map<?, ?> consumerProp) {
        this.consumerProp = consumerProp;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    protected void startProducer(Runnable producer, EventBasicLogger eventLogger) {

        String topicName = configReader.getInvalidMsgTopicName();
        long heartBeat = configReader.getInvalidMsgHeartBeat();
        int batchSize = configReader.getInvalidMsgBatchSize();

        if (eventLogger != null) {
            if (eventLogger instanceof ActivityLogger) {
                topicName = configReader.getActivityTopicName();
                heartBeat = configReader.getActivityHeartBeat();
                batchSize = configReader.getActivityBatchSize();
            } else if (eventLogger instanceof AppSystemLogger) {
                topicName = configReader.getAppSystemTopicName();
                heartBeat = configReader.getAppSystemHeartBeat();
                batchSize = configReader.getAppSystemBatchSize();
            } else if (eventLogger instanceof TransactionLogger) {
                topicName = configReader.getTransactionTopicName();
                heartBeat = configReader.getTransactionHeartBeat();
                batchSize = configReader.getTransactionBatchSize();
            } else if (eventLogger instanceof ThirdpartyLogger) {
                topicName = configReader.getThirdPartyTopicName();
                heartBeat = configReader.getThirdPartyHeartBeat();
                batchSize = configReader.getThirdPartyBatchSize();
            } else if (eventLogger instanceof MgmtActionLogger) {
                topicName = configReader.getMgmtActionTopicName();
                heartBeat = configReader.getMgmtActionHeartBeat();
                batchSize = configReader.getMgmtActionBatchSize();
            } else if (eventLogger instanceof OpenAPILogLogger) {
                topicName = configReader.getOpenAPILogTopicName();
                heartBeat = configReader.getOpenAPILogHeartBeat();
                batchSize = configReader.getOpenAPILogBatchSize();
            } else if (eventLogger instanceof UserBasicInfoLogger) {
                topicName = configReader.getUserInfoTopicName();
                heartBeat = configReader.getUserInfoHeartBeat();
                batchSize = configReader.getUserInfoBatchSize();
            }
        } else {
            throw new NullPointerException("EventLogger[" + topicName + "] init failed.");
        }

        try {
            logger.info("{} producer init finished...", eventLogger.getClass().getSimpleName());
            PRODUCER_EXECUTOR.scheduleAtFixedRate(producer, 0L, heartBeat, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            String errorMsg = "Start " + eventLogger.getClass().getSimpleName() + " producer with topic name[" +
                    topicName + "], batch size[" + batchSize + "] in heartbeat ["+ heartBeat +"] failed.";
            throw new RuntimeException(errorMsg, e);
        }
    }

    public boolean close() {
        logger.info("Producer close start...");

        // There are will do while sleep third when memory queue not empty
        if (activityLogger != null && activityLogger.size() > 0) {
            while (activityLogger.size() > 0) {
                activityLogger.sleep();
            }
            logger.info("Activity event queue is clean.");
        }

        if (transactionLogger != null && transactionLogger.size() > 0) {
            while (transactionLogger.size() > 0) {
                transactionLogger.sleep();
            }
            logger.info("Transaction event queue is clean.");
        }

        if (appSystemLogger != null && appSystemLogger.size() > 0) {
            while (appSystemLogger.size() > 0) {
                appSystemLogger.sleep();
            }
            logger.info("Application system event queue is clean.");
        }

        if (thirdPartyLogger != null && thirdPartyLogger.size() > 0) {
            while (thirdPartyLogger.size() > 0) {
                thirdPartyLogger.sleep();
            }
            logger.info("Third party event queue is clean.");
        }

        if (mgmtActionLogger != null && mgmtActionLogger.size() > 0) {
            while (mgmtActionLogger.size() > 0) {
                mgmtActionLogger.sleep();
            }
            logger.info("Management action event queue is clean.");
        }

        if (openAPILogLogger != null && openAPILogLogger.size() > 0) {
            while (openAPILogLogger.size() > 0) {
                openAPILogLogger.sleep();
            }
            logger.info("Open API log event queue is clean.");
        }

        if (userBasicInfoLogger != null && userBasicInfoLogger.size() > 0) {
            while (userBasicInfoLogger.size() > 0) {
                userBasicInfoLogger.sleep();
            }
            logger.info("User basic info event queue is clean.");
        }

        if (invalidMsgLogger != null && invalidMsgLogger.size() > 0) {
            while (invalidMsgLogger.size() > 0) {
                invalidMsgLogger.sleep();
            }
        }

       /* try {
            PRODUCER_EXECUTOR.shutdown();
        } catch (Exception e) {
            logger.error("Shutdown thread pool failed.", e);
            return false;
        }*/

        logger.info("Producer close end...");
        return true;
    }



    public void startActivityProducer() {
        startProducer(activityProducer, activityLogger);
    }

    public void startTransactionProducer() {
        startProducer(transactionProducer, transactionLogger);
    }

    public void startThirdPartyProducer(){
        startProducer(thirdPartyProducer, thirdPartyLogger);
    }

    public void startAppSystemProducer() {
        startProducer(appSystemProducer, appSystemLogger);
    }

    public void startMgmtActionProducer() {
        startProducer(mgmtActionProducer, mgmtActionLogger);
    }

    public void startOpenAPILogProducer() {
        startProducer(openAPIProducer, openAPILogLogger);
    }

    public void startUserBasicInfoProducer() {
        startProducer(userBasicInfoProducer, userBasicInfoLogger);
    }

    protected void startInvalidMsgProducer() {
        startProducer(invalidMsgProducer, invalidMsgLogger);
    }
}
