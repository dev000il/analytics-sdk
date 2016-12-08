package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.flatevent.FlatMgmtAction;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Management action log event
 *
 * @author senhui.li
 */
public class MgmtAction extends BasicEvent {

    // user identifier
    private UserIdentifier userIdentifier;
    /**
     * Management action
     */
    private String action;
    /**
     * Management detail message
     */
    private String message;

    @Override
    public void validate() throws PropertyInvalidException {
        super.validate();

        if (action == null || action.isEmpty()
                || message == null || message.isEmpty()) {
            throw new PropertyInvalidException("MgmtAction property [action] and [message] can't be NULL or empty.");
        }
    }

    public static MgmtAction createInstance() {
        return new MgmtAction();
    }

    public UserIdentifier createOrGetUserIdentifier() {
        if (this.userIdentifier == null) {
            this.userIdentifier = UserIdentifier.createInstance();
        }

        return this.userIdentifier;
    }

    @Override
    public FlatMgmtAction getFlat() {
        FlatMgmtAction flat = new FlatMgmtAction();
        super.setFlat(flat);

        flat.setA(this.action);
        flat.setMsg(this.message);

        if (this.userIdentifier != null) {
            flat.setPn(this.userIdentifier.getPhoneNumber());
            flat.setU(this.userIdentifier.getUserId());
            flat.setTu(this.userIdentifier.getTaobaoUserId());
            flat.setWu(this.userIdentifier.getWechatUserId());
        }

        return flat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MgmtAction{" +
                super.toString() +
                ", a='" + action + '\'' +
                ", msg='" + message + '\'' +
                "} ";
    }
}
