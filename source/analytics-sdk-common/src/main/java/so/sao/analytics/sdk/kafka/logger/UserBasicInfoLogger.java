package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.common.model.event.UserBasicInfo;
import so.sao.analytics.sdk.common.model.flatevent.FlatUserBasicInfo;

/**
 * Log user info events
 *
 * @author senhui.li
 */
public class UserBasicInfoLogger extends EventBasicLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(UserBasicInfoLogger.class.getSimpleName());

    private static UserBasicInfoLogger userBasicInfoLogger;

    public static UserBasicInfoLogger create() {
        if (userBasicInfoLogger == null) {
            synchronized (UserBasicInfoLogger.class) {
                if (userBasicInfoLogger == null) {
                    userBasicInfoLogger = new UserBasicInfoLogger();
                }
            }
        }
        return userBasicInfoLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static UserBasicInfoLogger get() {
        return userBasicInfoLogger;
    }

    public static void log(UserBasicInfo event) {
        userBasicInfoLogger.push(event);
    }

    public static void log(FlatUserBasicInfo event) {
        userBasicInfoLogger.push(event);
    }
}
