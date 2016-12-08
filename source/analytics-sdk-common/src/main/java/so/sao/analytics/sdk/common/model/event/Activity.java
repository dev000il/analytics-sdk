package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.sdk.common.model.property.PromotionInfo;
import so.sao.analytics.sdk.common.enums.ActivityType;
import so.sao.analytics.sdk.common.model.property.GainPoints;
import so.sao.analytics.sdk.common.model.property.ProductInfo;
import so.sao.analytics.sdk.common.model.property.ShopInfo;
import so.sao.analytics.sdk.common.model.property.TagInfo;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * All activities from platform
 *
 * @author senhui.li
 */
public class Activity extends BasicReward {

    private ProductInfo productInfo;
    private TagInfo tagInfo;
    // Enter lottery require Properties
    private PromotionInfo promotionInfo;
    private GainPoints gainPoints;
    // Shop
    private ShopInfo shopInfo;

    /**
     * Activity Type
     */
    private int activityType;
    /**
     * Sharing ID
     */
    private int sharingId;
    /**
     * Sharing Target
     */
    private int sharingTarget;

    public static Activity createInstance(int type) {
        Activity event = new Activity();
        event.setActivityType(type);
        return event;
    }

    public static Activity createInstance(ActivityType type) {
        Activity event = new Activity();
        event.setActivityType(type.getValue());
        return event;
    }

    public static Activity createUserScan() {
        return createInstance(ActivityType.ScanCode);
    }

    public static Activity createEnterLottery() {
        return createInstance(ActivityType.EnterLottery);
    }

    public static Activity createClaimReward() {
        return createInstance(ActivityType.ClaimReward);
    }

    public static Activity createSharing() {
        return createInstance(ActivityType.Sharing);
    }

    public ProductInfo createOrGetProductInfo() {
        if (this.productInfo == null) {
            this.productInfo = ProductInfo.createInstance();
        }

        return this.productInfo;
    }

    public TagInfo createOrGetTagInfo() {
        if (this.tagInfo == null) {
            this.tagInfo = TagInfo.createInstance();
        }

        return this.tagInfo;
    }

    public PromotionInfo createOrGetPromotionInfo() {
        if (this.promotionInfo == null) {
            this.promotionInfo = PromotionInfo.createInstance();
        }

        return this.promotionInfo;
    }

    public GainPoints createOrGetGainPoints() {
        if (this.gainPoints == null) {
            this.gainPoints = GainPoints.createInstance();
        }

        return this.gainPoints;
    }

    public ShopInfo createOrGetShopInfo() {
        if (this.shopInfo == null) {
            this.shopInfo = ShopInfo.createInstance();
        }

        return this.shopInfo;
    }

    @Override
    public void validate() throws PropertyInvalidException {

        super.validate();

        if (activityType <= 0) {
            throw new PropertyInvalidException("Activity property [activityType] can't be less than or equals to zero.");
        }

        if (activityType != ActivityType.Sharing.getValue() && tagInfo != null) {
            tagInfo.validate();
        }

        if (activityType == ActivityType.EnterLottery.getValue()) {
            if (promotionInfo !=  null) {
                promotionInfo.validate();
            }
        }

        if (activityType == ActivityType.ClaimReward.getValue()) {
            if (promotionInfo !=  null) {
                promotionInfo.validate();
            }

            if (physicalRewardInfo != null) {
                physicalRewardInfo.validate();
            }
        }

        if (activityType == ActivityType.Sharing.getValue()) {
            if (sharingId <= 0 || sharingTarget <= 0) {
                throw new PropertyInvalidException("Activity property [sharingId] and [sharingTarget] can't be less than or equals to zero.");
            }
        }

    }

    @Override
    public FlatActivity getFlat() {
        FlatActivity flat = new FlatActivity();
        super.setFlat(flat);

        if (this.productInfo != null) {
            flat.setPid(this.productInfo.getProductId());
            flat.setSid(this.productInfo.getSpecId());
        }

        if (this.tagInfo != null) {
            flat.setHid(this.tagInfo.getHonestId());
            flat.setNum(this.tagInfo.getSequenceNumber());
            flat.setBid(this.tagInfo.getBatchId());
            flat.setDid(this.tagInfo.getDistributorId());
            flat.setOrn(this.tagInfo.getOrderNumber());
            flat.setSdid(this.tagInfo.getSoleDistributorId());
        }

        if (this.promotionInfo != null) {
            flat.setPri(this.promotionInfo.getPromotionId());
            flat.setPnu(this.promotionInfo.isPromotionNewUser());
        }

        if (this.gainPoints != null) {
            flat.setPtri(this.gainPoints.getPointRuleId());
            flat.setPts(this.gainPoints.getPointsAwarded());
        }

        if (this.shopInfo != null) {
            flat.setShi(this.shopInfo.getShopId());
        }

        flat.setAt(this.activityType);
        flat.setSi(this.sharingId);
        flat.setSt(this.sharingTarget);

        return flat;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getSharingId() {
        return sharingId;
    }

    public void setShring(int id, int target) {
        setSharingId(id);
        setSharingTarget(target);
    }

    public void setSharingId(int sharingId) {
        this.sharingId = sharingId;
    }

    public int getSharingTarget() {
        return sharingTarget;
    }

    public void setSharingTarget(int sharingTarget) {
        this.sharingTarget = sharingTarget;
    }

    @Override
    public String toString() {
        return "Activity {" +
                super.toString() +
                (productInfo != null ? productInfo : "") +
                (tagInfo != null ? tagInfo : "") +
                (promotionInfo != null ? promotionInfo : "") +
                (gainPoints != null ? gainPoints : "") +
                (shopInfo != null ? shopInfo : "") +
                ", at=" + activityType +
                ", si=" + sharingId +
                ", st=" + sharingTarget +
                "} ";
    }
}
