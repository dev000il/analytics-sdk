package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.enums.ThirdpartyType;
import so.sao.analytics.sdk.common.model.flatevent.FlatThirdparty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Third party integration event
 *
 * @author senhui.li
 */
public class Thirdparty extends BasicReward {

    /**
     * Third party Type
     */
    private int thirdPartyType;

    // ============ MengTuo ============
    /**
     * Debit Point Type ID
     */
    private int pointType;
    /**
     * Detail point
     */
    private int detailPoint;
    /**
     * Order ID
     */
    private String orderId;
    /**
     * Order quantity
     */
    private int orderQunatity;
    /**
     * Order points amount
     */
    private int orderPoints;

    // ============ LiuSha ============

    /**
     * Company New User
     */
    private boolean companyNewUser;
    /**
     * Liu Sha Activity Name
     */
    private String liuShaActName;
    /**
     * Liu Sha Reward Amount
     */
    private String liuShaRewardAmt;

    // ============ Mobile Datawith Claim ============
    /**
     * bandwidth packet size
     */
    private int packetSize;
    /**
     * bandwidth operator id
     */
    private String operatorId;


    public static Thirdparty createInstance(ThirdpartyType type) {
        Thirdparty event = new Thirdparty();
        event.setThirdPartyType(type.getVlaue());
        event.addMetadata = event.createOrGetAddMetadata();

        return event;
    }

    public static Thirdparty createInstance(int type) {
        Thirdparty event = new Thirdparty();
        event.setThirdPartyType(type);
        event.addMetadata = event.createOrGetAddMetadata();

        return event;
    }

    public static Thirdparty createMengTuoClaim() {
        return createInstance(ThirdpartyType.MengTuoClaim);
    }

    public static Thirdparty createMengTuoCancel() {
        return createInstance(ThirdpartyType.MengTuoCancel);
    }

    public static Thirdparty createLiuShaSign() {
        return createInstance(ThirdpartyType.LiuSha);
    }

    /**
     * Create Instance for mobile data bandwidth top up
     * @return Thirdparty
     */
    public static Thirdparty createMDBandwidthTopUp() {
        return createInstance(ThirdpartyType.MobileBandwidthTopUp);
    }

    public static Thirdparty createUserRegisterTarget() {
        return createInstance(ThirdpartyType.UserRegisterTarget);
    }

    @Override
    public void validate() throws PropertyInvalidException {

        super.validate();

        if (thirdPartyType <= 0) {
            throw new PropertyInvalidException("property [thirdPartyType] can't be less than or equals to zero.");
        }

        if (thirdPartyType == ThirdpartyType.MengTuoClaim.getVlaue()
                || thirdPartyType == ThirdpartyType.MengTuoCancel.getVlaue()) {
            if (pointType <= 0 || detailPoint <= 0 || orderId == null || orderId.isEmpty() || orderQunatity <= 0
                    || orderQunatity <= 0) {
                throw new PropertyInvalidException("MengTuo property [pointType], [detailPoint], [orderQunatity], [orderQunatity] " +
                        "can't be less than or equals to zero, and [orderId] can't be NULL or empty.");
            }
        }

        if (thirdPartyType == ThirdpartyType.LiuSha.getVlaue()) {
            if (liuShaActName == null || liuShaActName.isEmpty() || liuShaRewardAmt == null || liuShaRewardAmt.isEmpty()) {
                throw new PropertyInvalidException("LiuSha property [liuShaActName] and [liuShaRewardAmt] can't be NULL or empty.");
            }
        }

        if (thirdPartyType == ThirdpartyType.MobileBandwidthTopUp.getVlaue()){
            if (operatorId == null || packetSize <= 0) {
                throw new PropertyInvalidException(
                        "Bandwidth property [PacketSize] and [OperatorId] can't be equal or less than zero.");
            }
        }

    }

    @Override
    public FlatThirdparty getFlat() {
        FlatThirdparty flat = new FlatThirdparty();
        super.setFlat(flat);

        flat.setTpt(this.thirdPartyType);
        flat.setSupp(this.addMetadata.getSupplementaryFiled());

        return flat;
    }

    public void setMengTuo(int debitPointType, int points, String orId, int orderQty, int orderPointAmt) {
        setPointType(debitPointType);
        setDetailPoint(points);
        setOrderId(orId);
        setOrderQunatity(orderQty);
        setOrderPoints(orderPointAmt);
    }

    public void setLiuSha(boolean newUser, String actName, String rewardAmt) {
        setCompanyNewUser(newUser);
        setLiuShaActName(actName);
        setLiuShaRewardAmt(rewardAmt);
    }

    public void setThroughputClaim(int packetSize, String operatorId) {
        setPacketSize(packetSize);
        setOperatorId(operatorId);
    }

    public int getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(int thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
        this.addMetadata.setDebitPointType(pointType);
    }

    public int getDetailPoint() {
        return detailPoint;
    }

    public void setDetailPoint(int detailPoint) {
        this.detailPoint = detailPoint;
        this.addMetadata.setDebitPointDetail(detailPoint);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        this.addMetadata.setOrderId(orderId);
    }

    public int getOrderQunatity() {
        return orderQunatity;
    }

    public void setOrderQunatity(int orderQunatity) {
        this.orderQunatity = orderQunatity;
        this.addMetadata.setOrderQuantity(orderQunatity);
    }

    public int getOrderPoints() {
        return orderPoints;
    }

    public void setOrderPoints(int orderPoints) {
        this.orderPoints = orderPoints;
        this.addMetadata.setOrderPoints(orderPoints);
    }

    public boolean isCompanyNewUser() {
        return companyNewUser;
    }

    public void setCompanyNewUser(boolean companyNewUser) {
        this.companyNewUser = companyNewUser;
        this.addMetadata.setCompanyNewUser(companyNewUser);
    }

    public String getLiuShaActName() {
        return liuShaActName;
    }

    public void setLiuShaActName(String liuShaActName) {
        this.liuShaActName = liuShaActName;
        this.addMetadata.setLiuShaActivityName(liuShaActName);
    }

    public String getLiuShaRewardAmt() {
        return liuShaRewardAmt;
    }

    public void setLiuShaRewardAmt(String liuShaRewardAmt) {
        this.liuShaRewardAmt = liuShaRewardAmt;
        this.addMetadata.setLiuShaRewardAmout(liuShaRewardAmt);
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
        this.addMetadata.setPacketSize(packetSize);
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        this.addMetadata.setOperatorId(operatorId);
    }

    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer("ThirdParty{");
        tmp.append(super.toString());
        tmp.append(", tpt=").append(thirdPartyType);
        if (thirdPartyType == ThirdpartyType.MobileBandwidthTopUp.getVlaue()) {
            tmp.append(", pks=").append(packetSize);
            tmp.append(", opid=").append(operatorId);
        }
        else if (thirdPartyType == ThirdpartyType.LiuSha.getVlaue()) {
            tmp.append(", cnu=").append(companyNewUser);
            tmp.append(", lsan='").append(liuShaActName).append('\'');
            tmp.append(", lsra='").append(liuShaRewardAmt).append('\'');
        }
        else if (thirdPartyType == ThirdpartyType.MengTuoClaim.getVlaue()
                || thirdPartyType == ThirdpartyType.MengTuoCancel.getVlaue()) {
            tmp.append(", tpt=").append(thirdPartyType);
            tmp.append(", dpt=").append(pointType);
            tmp.append(", dpd=").append(detailPoint);
            tmp.append(", oi='").append(orderId).append('\'');
            tmp.append(", oq=").append(orderQunatity);
            tmp.append(", op=").append(orderPoints);
        }

        tmp.append("}");
        return tmp.toString();
    }
}
