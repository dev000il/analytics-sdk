package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import so.sao.analytics.sdk.common.model.event.Transaction;
import so.sao.analytics.sdk.common.model.flatevent.FlatTransaction;

/**
 * Log transaction events
 *
 * @author senhui.li
 */
public class TransactionLogger extends EventBasicLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionLogger.class.getSimpleName());

    private static TransactionLogger transactionLogger;

    public static TransactionLogger create() {
        if (transactionLogger == null) {
            synchronized (TransactionLogger.class) {
                if (transactionLogger == null) {
                    transactionLogger = new TransactionLogger();
                }
            }
        }
        return transactionLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static TransactionLogger get() {
        return transactionLogger;
    }

    public static void log(Transaction event) {
        transactionLogger.push(event);
    }

    public static void log(FlatTransaction event) {
        transactionLogger.push(event);
    }
}
