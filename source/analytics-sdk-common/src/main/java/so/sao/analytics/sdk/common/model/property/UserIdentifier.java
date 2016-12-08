package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * User Identifier Property
 *
 * @author senhui.li
 */
public class UserIdentifier implements CheckProperty {

    /**
     * Phone Number
     */
    private String phoneNumber;
    /**
     * User ID
     */
    private String userId;
    /**
     * Taobao User ID
     */
    private String taobaoUserId;
    /**
     * Wechat User ID
     */
    private String wechatUserId;

    public static UserIdentifier createInstance() {
        return new UserIdentifier();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setUserIdentifier(String phoneNumber, String userId, String taobaoUserId, String wechatUserId) {
        setPhoneNumber(phoneNumber);
        setUserId(userId);
        setTaobaoUserId(taobaoUserId);
        setWechatUserId(wechatUserId);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaobaoUserId() {
        return taobaoUserId;
    }

    public void setTaobaoUserId(String taobaoUserId) {
        this.taobaoUserId = taobaoUserId;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    @Override
    public String toString() {
        return ", pn='" + (phoneNumber==null ? "" : phoneNumber) + '\'' +
                ", u='" + (userId==null ? "" : userId) + '\'' +
                ", tu='" + (taobaoUserId==null ? "" : taobaoUserId) + '\'' +
                ", wu='" + (wechatUserId==null ? "" : wechatUserId) + '\'';
    }
}
