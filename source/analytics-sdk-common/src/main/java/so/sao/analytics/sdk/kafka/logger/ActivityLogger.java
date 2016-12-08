package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.sdk.common.model.event.Activity;

/**
 * Log activities events
 *
 * @author senhui.li
 */
public class ActivityLogger extends EventBasicLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(ActivityLogger.class.getSimpleName());

    private static ActivityLogger activityLogger;

    public static ActivityLogger create() {
        if (activityLogger == null) {
            synchronized (ActivityLogger.class) {
                if (activityLogger == null) {
                    activityLogger = new ActivityLogger();
                }
            }
        }

        return activityLogger;
    }
    
    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static ActivityLogger get() {
        return activityLogger;
    }

    public static  void log(Activity event) {
        activityLogger.push(event);
    }

    public static void log(FlatActivity event) {
        activityLogger.push(event);
    }
}
