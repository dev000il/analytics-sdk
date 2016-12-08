package so.sao.analytics.sdk.common.model;

/**
 * The getTopicName settings interface
 *
 * @author senhui.li
 */
public interface TopicSettings {

    /**
     * The flag whether is kafka running
     *
     * @return running flag
     */
    boolean isRunning();

    /**
     * The kafka getTopicDB path
     *
     * @return topic DB path
     */
    String getTopicDBPath();

    /**
     * The kafka getTopicName name
     *
     * @return topic name
     */
    String getTopicName();

    /**
     * The time of kafka sender heartbeatInMs in million
     *
     * @return million time
     */
    long getHeartbeatInMS();

    /**
     * The size of kafka sender batch
     *
     * @return batch size
     */
    int getBatchSize();
}
