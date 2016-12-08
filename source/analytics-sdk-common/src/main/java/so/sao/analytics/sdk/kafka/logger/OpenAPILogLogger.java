package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;
import so.sao.analytics.sdk.common.model.event.OpenAPILog;

/**
 * Log openapilog events
 *
 * @author senhui.li
 */
public class OpenAPILogLogger extends EventBasicLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenAPILogLogger.class.getSimpleName());

    private static OpenAPILogLogger openAPILogLogger;

    public static OpenAPILogLogger create() {
        if (openAPILogLogger == null) {
            synchronized (OpenAPILogLogger.class) {
                if (openAPILogLogger == null) {
                    openAPILogLogger = new OpenAPILogLogger();
                }
            }
        }

        return openAPILogLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static OpenAPILogLogger get() {
        return openAPILogLogger;
    }

    public static void log(OpenAPILog event) {
        openAPILogLogger.push(event);
    }

    public static void log(FlatOpenAPILog event) {
        openAPILogLogger.push(event);
    }
}
