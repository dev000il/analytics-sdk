package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Claim or other Action Result
 *
 * @author senhui.li
 */
public class ActionResult implements CheckProperty {

    /**
     * Success status: 0 unknown, 1 success, 2 failure, 3 claim later
     */
    private int successStatus;
    /**
     * Success Detail Message
     */
    private String successMessage;

    public static ActionResult createInstance() {
        return new ActionResult();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setActionResult(int successStatus, String successMessage) {
        setSuccessMessage(successMessage);
        setSuccessStatus(successStatus);
    }

    public int getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(int successStatus) {
        this.successStatus = successStatus;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @Override
    public String toString() {
        return ", okid=" + successStatus +
                ", ok='" + successMessage + '\'';
    }
}
