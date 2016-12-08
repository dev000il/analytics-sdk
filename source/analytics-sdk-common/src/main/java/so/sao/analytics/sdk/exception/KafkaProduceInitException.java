package so.sao.analytics.sdk.exception;

/**
 * Throw an exception when init producer failed
 *
 * @author senhui.li
 */
public class KafkaProduceInitException extends RuntimeException {

    private static final long serialVersionUID = -8583876531592505998L;

    public KafkaProduceInitException() {
        super();
    }

    public KafkaProduceInitException(String message) {
        super(message);
    }

    public KafkaProduceInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public KafkaProduceInitException(Throwable cause) {
        super(cause);
    }
}
