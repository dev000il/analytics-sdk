package so.sao.analytics.sdk.kafka.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import so.sao.analytics.sdk.common.model.event.InvalidMessage;

/**
 * There is special message logger for handle all event's invalid message.
 *
 * @author senhui.li
 */
public class InvalidMsgLogger extends EventBasicLogger {

    private static final Logger logger = LoggerFactory.getLogger(InvalidMsgLogger.class.getSimpleName());

    private static InvalidMsgLogger invalidMsgLogger;

    public static InvalidMsgLogger create() {
        if (invalidMsgLogger == null) {
            synchronized (InvalidMsgLogger.class) {
                if (invalidMsgLogger == null) {
                    invalidMsgLogger = new InvalidMsgLogger();
                }
            }
        }

        return invalidMsgLogger;
    }

    public void push(InvalidMessage event) {
        try {
            // Write out the invalid message on disk
            loggerInfo(event.getClass().getSimpleName()+
                    JSON.toJSONString(event.getFlat().getAll()));

            byte[] message = getKryoSerialize(event.getFlat());
            this.queue.add(message);
        } catch (Exception e) {
            loggerError("Add invalid event byte array data to queue failed.", e);
        }
    }

    public static InvalidMsgLogger get() {
        return invalidMsgLogger;
    }

    @Override
    protected void loggerInfo(String message) {
        logger.info(message);
    }

    public static void log(InvalidMessage event) {
        invalidMsgLogger.push(event);
    }
}
