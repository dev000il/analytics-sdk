package so.sao.analytics.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Reader Kafka configure file
 *
 * @author senhui.li
 */
public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static final String DEF_CONFIG_PATH = "/platform-config.properties";
    public static final String AWS_DEF_CONFIG_PATH = "/aws-platform-config.properties";

    private static Properties setting = new Properties();
    private static ConfigReader configReader;

    private String configPath;

    /**
     * Create or get the configure file reader instance
     * @param configFile    config file path
     */
    public static ConfigReader createOrGetInstance(String configFile) {
        if (configReader == null) {
            synchronized (ConfigReader.class) {
                if (configReader == null) {
                    configReader = new ConfigReader(configFile);
                }
            }
        }

        return configReader;
    }

    /**
     * Add a config file path setting for distinguish Kafka or Kinesis ENV
     *
     * @param configFile
     */
    public ConfigReader(String configFile) {

        if (configFile==null || configFile.isEmpty()) {
            setConfigPath(DEF_CONFIG_PATH);
        } else {
            setConfigPath(configFile);
        }
    }

    public String getConfigPath() {
        return configPath;
    }

    /**
     * reload the config file
     * @param configPath config file path
     */
    public void setConfigPath(String configPath) {
        this.configPath = configPath;

        loadFile();
    }

    protected void loadFile() {
        InputStream is = this.getClass().getResourceAsStream(configPath);
        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            setting.load(reader);

            if (logger.isInfoEnabled()) {
                logger.info("============================================================");
                for (String name : setting.stringPropertyNames()) {
                    logger.info("{}: {}", name, setting.getProperty(name));
                }
                logger.info("============================================================");
            }
        } catch (IOException e) {
            logger.error("Load platform configure file["+ configPath +"] failed.", e);
            throw new RuntimeException(e);
        }
    }

    public int machineEnv() {
        return Integer.parseInt(setting.getProperty("machine.environment"));
    }

    public String getAWSAccessKey() {
        return setting.getProperty("aws.accesskey");
    }

    public String getAWSSecretKey() {
        return setting.getProperty("aws.secretkey");
    }

    public String getAWSRegionName() {
        return setting.getProperty("aws.region.name");
    }

    public String getKinesisEndPointName() {
        return setting.getProperty("kinesis.endPoint");
    }

    public String getKinesisCloudWatchEndPoint() {
        return setting.getProperty("kinesis.cloudWatch.endPoint");
    }

    public int getKinesisMaxConnect() {
        return Integer.parseInt(setting.getProperty("kinesis.max.connection"));
    }

    public int getKinesisRequestTimeout() {
        return Integer.parseInt(setting.getProperty("kinesis.request.timeout"));
    }

    public int getKinesisRecordsMaxBufferedTime() {
        return Integer.parseInt(setting.getProperty("kinesis.records.max.buffered.time"));
    }

    public boolean isKafkaRunning() {
        return Boolean.valueOf(setting.getProperty("kafka.running.flag"));
    }

    public String getActivityTopicName() {
        return setting.getProperty("activity.topic.name");
    }

    public long getActivityHeartBeat() {
        return Long.valueOf(setting.getProperty("activity.task.heartbeat")).longValue();
    }

    public int getActivityBatchSize() {
        return Integer.parseInt(setting.getProperty("activity.batch.size"));
    }

    public String getTransactionTopicName() {
        return setting.getProperty("transaction.topic.name");
    }

    public long getTransactionHeartBeat() {
        return Long.valueOf(setting.getProperty("transaction.task.heartbeat")).longValue();
    }

    public int getTransactionBatchSize() {
        return Integer.parseInt(setting.getProperty("transaction.batch.size"));
    }

    public String getThirdPartyTopicName() {
        return setting.getProperty("thirdparty.topic.name");
    }

    public long getThirdPartyHeartBeat() {
        return Long.valueOf(setting.getProperty("thirdparty.task.heartbeat"));
    }

    public int getThirdPartyBatchSize() {
        return Integer.valueOf(setting.getProperty("thirdparty.batch.size"));
    }

    public String getAppSystemTopicName() {
        return setting.getProperty("appsystem.topic.name");
    }

    public long getAppSystemHeartBeat() {
        return Long.valueOf(setting.getProperty("appsystem.task.heartbeat")).longValue();
    }

    public int getAppSystemBatchSize() {
        return Integer.parseInt(setting.getProperty("appsystem.batch.size"));
    }

    public String getMgmtActionTopicName() {
        return setting.getProperty("mgmtaction.topic.name");
    }

    public long getMgmtActionHeartBeat() {
        return Long.valueOf(setting.getProperty("mgmtaction.task.heartbeat")).longValue();
    }

    public int getMgmtActionBatchSize() {
        return Integer.parseInt(setting.getProperty("mgmtaction.batch.size"));
    }

    public String getOpenAPILogTopicName() {
        return setting.getProperty("openapilog.topic.name");
    }

    public long getOpenAPILogHeartBeat() {
        return Long.valueOf(setting.getProperty("openapilog.task.heartbeat")).longValue();
    }

    public int getOpenAPILogBatchSize() {
        return Integer.parseInt(setting.getProperty("openapilog.batch.size"));
    }

    public String getUserInfoTopicName() {
        return setting.getProperty("userinfo.topic.name");
    }

    public long getUserInfoHeartBeat() {
        return Long.valueOf(setting.getProperty("userinfo.task.heartbeat")).longValue();
    }

    public int getUserInfoBatchSize() {
        return Integer.parseInt(setting.getProperty("userinfo.batch.size"));
    }

    public String getInvalidMsgTopicName() {
        return setting.getProperty("invalidmsg.topic.name");
    }

    public long getInvalidMsgHeartBeat() {
        return Long.valueOf(setting.getProperty("invalidmsg.task.heartbeat")).longValue();
    }

    public int getInvalidMsgBatchSize() {
        return Integer.parseInt(setting.getProperty("invalidmsg.batch.size"));
    }

}
