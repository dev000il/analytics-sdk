package so.sao.analytics.sdk.exception;

/**
 * Throws an exception when valid all common property fields
 *
 * @author senhui.li
 */
public class PropertyInvalidException extends Exception {

    private static final long serialVersionUID = -2072286530305320730L;

    public PropertyInvalidException() {
        super();
    }

    public PropertyInvalidException(String message) {
        super(message);
    }

    public PropertyInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyInvalidException(Throwable cause) {
        super(cause);
    }

    protected PropertyInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
