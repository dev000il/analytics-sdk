package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.analytics.sdk.common.model.event.AppSystem;

/**
 * Log Application events
 *
 * @author senhui.li
 */
public class AppSystemLogger extends EventBasicLogger {

    private static final Logger logger = LoggerFactory.getLogger(AppSystemLogger.class.getSimpleName());

    private static AppSystemLogger appSystemLogger;

    public static AppSystemLogger create() {
        if (appSystemLogger == null) {
            synchronized (AppSystemLogger.class) {
                if (appSystemLogger == null) {
                    appSystemLogger = new AppSystemLogger();
                }
            }
        }

        return appSystemLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static AppSystemLogger get() {
        return appSystemLogger;
    }

    public static void log(AppSystem event) {
        appSystemLogger.push(event);
    }
}
