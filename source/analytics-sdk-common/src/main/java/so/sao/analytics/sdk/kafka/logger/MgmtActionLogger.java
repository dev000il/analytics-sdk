package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.analytics.sdk.common.model.event.MgmtAction;
import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;

/**
 * Log Application events
 *
 * @author senhui.li
 */
public class MgmtActionLogger extends EventBasicLogger {

    private static final Logger logger = LoggerFactory.getLogger(MgmtActionLogger.class.getSimpleName());

    private static MgmtActionLogger mgmtActionLogger;

    public static MgmtActionLogger create() {
        if (mgmtActionLogger == null) {
            synchronized (MgmtActionLogger.class) {
                if (mgmtActionLogger == null) {
                    mgmtActionLogger = new MgmtActionLogger();
                }
            }
        }

        return mgmtActionLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static MgmtActionLogger get() {
        return mgmtActionLogger;
    }

    public static void log(MgmtAction event) {
        mgmtActionLogger.push(event);
    }


    public static void log(FlatOpenAPILog event) {
        mgmtActionLogger.push(event);
    }
}
