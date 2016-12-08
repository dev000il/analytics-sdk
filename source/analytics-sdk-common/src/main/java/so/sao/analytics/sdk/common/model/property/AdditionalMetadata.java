package so.sao.analytics.sdk.common.model.property;

import java.util.HashMap;
import java.util.Map;

import so.sao.analytics.sdk.common.SuppFieldsNameSpace;
import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Additional Metadata Property
 *
 * @author senhui.li
 */
public class AdditionalMetadata implements CheckProperty {

    /**
     * Identifying Public Label
     */
    private String identifyPublicLabel;
    /**
     * Supplementary filed
     */
    private Map<String, Object> supplementaryFiled = new HashMap<>();

    public static AdditionalMetadata createInstance() {
        return new AdditionalMetadata();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setAdditionalMetadata(String identifyPublicLabel, Map<String, Object> supplementaryFiled) {
        setIdentifyPublicLabel(identifyPublicLabel);
        setSupplementaryFiled(supplementaryFiled);

    }

    public String getIdentifyPublicLabel() {
        return identifyPublicLabel;
    }

    public void setIdentifyPublicLabel(String identifyPublicLabel) {
        this.identifyPublicLabel = identifyPublicLabel;
    }

    public Map<String, Object> getSupplementaryFiled() {
        return supplementaryFiled;
    }

    public void setSupplementaryFiled(Map<String, Object> supplementaryFiled) {
        if (supplementaryFiled != null && supplementaryFiled.isEmpty()) {
            this.supplementaryFiled.putAll(supplementaryFiled);
        }
    }

    public void appendSupplementFiled(String key, Object value) {
        this.supplementaryFiled.put(key, value);
    }

    public String getSuppStringFiled(String key) {
        return this.supplementaryFiled.containsKey(key) ? this.supplementaryFiled.get(key).toString() : null;
    }

    public Boolean getSuppBooleanFiled(String key) {
        return this.supplementaryFiled.containsKey(key) ? Boolean.valueOf(this.supplementaryFiled.get(key).toString()) : false;
    }

    public int getSuppIntFiled(String key) {
        return this.supplementaryFiled.containsKey(key) ? Integer.parseInt(this.supplementaryFiled.get(key).toString()) : 0;
    }

    public long getSuppLongFiled(String key) {
        return this.supplementaryFiled.containsKey(key) ? Long.parseLong(this.supplementaryFiled.get(key).toString()) : 0L;
    }

    public void setNewTagScan(boolean newTagScan) {
        this.appendSupplementFiled(SuppFieldsNameSpace.NEW_TAG_SCAN_FLAG, newTagScan ? "1" : "0");
    }


    public void setAdvisorPhoneNum(String phoneNum) {
        this.appendSupplementFiled(SuppFieldsNameSpace.ADVISOR_PHONE_NUM, phoneNum);
    }

    public void setRecommenderPhoneNum(String phoneNum) {
        this.appendSupplementFiled(SuppFieldsNameSpace.RECOMMENDER_PHONE_NUM, phoneNum);
    }

    public void setContinueScanCount(int continueScanCount) {
        this.appendSupplementFiled(SuppFieldsNameSpace.CONTINUE_SCAN_COUNT, continueScanCount);
    }

    public void setRemark(String remark) {
        this.appendSupplementFiled(SuppFieldsNameSpace.REMARK, remark);
    }

    public void setFirstSharing(boolean firstSharing) {
        this.appendSupplementFiled(SuppFieldsNameSpace.FIRST_SHARING, firstSharing);
    }

    public void setFromUserId(String fromUserId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.FROM_USER_ID, fromUserId);
    }

    public void setToUserId(String toUserId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.TO_USER_ID, toUserId);
    }

    public void setDebitPointType(int pointType) {
        this.appendSupplementFiled(SuppFieldsNameSpace.DEBIT_POINT_TYPE, pointType);
    }

    public void setDebitPointDetail(int pointDetail) {
        this.appendSupplementFiled(SuppFieldsNameSpace.DEBIT_POINT_DETAIL, pointDetail);
    }

    public void setOrderId(String orderId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.ORDER_ID, orderId);
    }

    public void setOrderQuantity(int quantity) {
        this.appendSupplementFiled(SuppFieldsNameSpace.ORDER_QUANTITY, quantity);
    }

    public void setOrderPoints(int points) {
        this.appendSupplementFiled(SuppFieldsNameSpace.ORDER_POINTS, points);
    }

    public void setCompanyNewUser(boolean newUser) {
        this.appendSupplementFiled(SuppFieldsNameSpace.COMPANY_NEW_USER, newUser);
    }

    public void setLiuShaActivityName(String activityName) {
        this.appendSupplementFiled(SuppFieldsNameSpace.LIUSHA_ACTIVITY_NAME, activityName);
    }

    public void setLiuShaRewardAmout(String rewardAmout) {
        this.appendSupplementFiled(SuppFieldsNameSpace.LIUSHA_REWARD_AMOUNT, rewardAmout);
    }

    public void setWechatServeId(String wechatServeId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.WECHAT_SERVE_ID, wechatServeId);
    }

    public void setPresentId(String presentId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PRESENT_ID, presentId);
    }

    public void setPresentClaimFlag(boolean presentClaimFlag) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PRESENT_CLAIM_FLAG, presentClaimFlag);
    }

    public void setPresentCancelStatus(String presentCancelStatus) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PRESENT_CANCEL_STATUS, presentCancelStatus);
    }

    public void setPresentUserId(String presentUserId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PRESENT_USER_ID, presentUserId);
    }

    public void setPresentRemark(String presentRemark) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PRESENT_REMARK, presentRemark);
    }

    public void setPacketSize(int packetSize) {
        this.appendSupplementFiled(SuppFieldsNameSpace.PACKET_SIZE, packetSize);
    }

    public void setOperatorId(String operatorId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.OPERATOR_ID, operatorId);
    }

    public void setEventType(String eventType) {
        this.appendSupplementFiled(SuppFieldsNameSpace.EVENT_TYPE, eventType);
    }

    public void setFlatEventSource(String flatEventSource) {
        this.appendSupplementFiled(SuppFieldsNameSpace.FLAT_EVENT_SOURCE, flatEventSource);
    }

    public void setJDUserType(String jdUserType) {
       this.appendSupplementFiled(SuppFieldsNameSpace.JD_USER_TYPE, jdUserType);
    }

    public void setJDCouponId(String jdCouponId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.JD_COUPON_ID, jdCouponId);
    }

    public void setCouponId(String couponId) {
        this.appendSupplementFiled(SuppFieldsNameSpace.COUPON_ID, couponId);
    }

    public void setRegisterTargetType(int targetType) {
        this.appendSupplementFiled(SuppFieldsNameSpace.REGISTER_TARGET_TYPE, targetType);
    }

    @Override
    public String toString() {
        return ", ipl='" + (identifyPublicLabel==null ? "" : identifyPublicLabel) + '\'' +
                ", supp=" + supplementaryFiled;
    }
}
