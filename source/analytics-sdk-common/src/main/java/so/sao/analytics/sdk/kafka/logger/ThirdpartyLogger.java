package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import so.sao.analytics.sdk.common.model.event.Thirdparty;
import so.sao.analytics.sdk.common.model.flatevent.FlatThirdparty;

/**
 * Third party events log
 *
 * @author senhui.li
 */
public class ThirdpartyLogger extends EventBasicLogger {

    private static final Logger logger = LoggerFactory.getLogger(ThirdpartyLogger.class.getSimpleName());

    private static ThirdpartyLogger thirdpartyLogger;

    public static ThirdpartyLogger create() {
        if (thirdpartyLogger == null) {
            synchronized (ThirdpartyLogger.class) {
                if (thirdpartyLogger == null) {
                    thirdpartyLogger = new ThirdpartyLogger();
                }
            }
        }
        return thirdpartyLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static ThirdpartyLogger get() {
        return thirdpartyLogger;
    }

    public static void log(Thirdparty event) {
        thirdpartyLogger.push(event);
    }


    public static void log(FlatThirdparty event) {
        thirdpartyLogger.push(event);
    }
}
