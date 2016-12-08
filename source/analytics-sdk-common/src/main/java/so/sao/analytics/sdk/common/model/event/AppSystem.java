package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.flatevent.FlatAppSystem;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * HD application system event
 *
 * @author senhui.li
 */
public class AppSystem extends BasicEvent {

    /**
     * App Event ID
     */
    private int appEventId;
    /**
     * HengDa App Event ID
     */
    private int HDAppEventId;
    /**
     * Log Level
     */
    private String logLevel;
    /**
     * Event message
     */
    private String message;

    @Override
    public void validate() throws PropertyInvalidException {

        super.validate();

        if (logLevel == null || logLevel.isEmpty() || message == null || message.isEmpty()) {
            throw new PropertyInvalidException("Application system property [logLevel] and [message] can't be NULL or empty.");
        }

    }

    public static AppSystem createInstance() {
        AppSystem event = new AppSystem();
        // -2 means program exception or info message
        event.setCompanyId(-2);
        return event;
    }

    public void setAppSystem(int eventId, int HDEventId, String level, String mesg) {
        appEventId = eventId;
        HDAppEventId = HDEventId;
        logLevel = level;
        message = mesg;
    }

    @Override
    public FlatAppSystem getFlat() {
        FlatAppSystem flat = new FlatAppSystem();
        super.setFlat(flat);

        flat.setAe(this.appEventId);
        flat.setHae(this.HDAppEventId);
        flat.setL(this.logLevel);
        flat.setMsg(this.message);

        return flat;
    }

    public int getAppEventId() {
        return appEventId;
    }

    public void setAppEventId(int appEventId) {
        this.appEventId = appEventId;
    }

    public int getHDAppEventId() {
        return HDAppEventId;
    }

    public void setHDAppEventId(int HDAppEventId) {
        this.HDAppEventId = HDAppEventId;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AppSystem{" +
                super.toString() +
                ", ae=" + appEventId +
                ", hae=" + HDAppEventId +
                ", l='" + logLevel + '\'' +
                ", msg='" + message + '\'' +
                "} ";
    }
}
