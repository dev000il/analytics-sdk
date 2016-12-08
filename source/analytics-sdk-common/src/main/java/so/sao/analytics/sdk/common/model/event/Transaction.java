package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.enums.TransactionType;
import so.sao.analytics.sdk.common.model.flatevent.FlatTransaction;
import so.sao.analytics.sdk.common.model.property.GainPoints;
import so.sao.analytics.sdk.common.model.property.ShopInfo;
import so.sao.analytics.sdk.common.model.property.SpentPoints;
import so.sao.analytics.sdk.common.model.property.TagInfo;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * All transaction from platform
 *
 * @author senhui.li
 */
public class Transaction extends BasicReward {

    // Enter point lottery require properties
    private TagInfo tagInfo;
    private SpentPoints spentPoints;
    private GainPoints gainPoints;
    // Shop
    private ShopInfo shopInfo;

    /**
     * Transaction Type Id {@link TransactionType}
     */
    private int transactionType;
    /**
     * Point lottery id
     *
     * greater than 0 means point lottery
     *
     * equals -1 means mall exchange
     */
    private int lotteryId;

    public static Transaction createInstance(int type) {
        Transaction event = new Transaction();
        event.setTransactionType(type);
        return event;
    }

    public static Transaction createInstance(TransactionType type) {
        Transaction event = new Transaction();
        event.setTransactionType(type.getValue());
        return event;
    }

    public static Transaction createPointLottery() {
        return createInstance(TransactionType.PointLottery);
    }

    public static Transaction createMallExchange() {
        return createInstance(TransactionType.ExchangeMallReward);
    }

    public static Transaction createPointLotteryClaim() {
        return createInstance(TransactionType.ClaimPointLotteryReward);
    }

    public static Transaction createPointGame() {
        return createInstance(TransactionType.PointGame);
    }

    public static Transaction createClaimPointGameReward() {
        return createInstance(TransactionType.ClaimPointGameReward);
    }

    public static Transaction createChanceGame() {
        return createInstance(TransactionType.ChanceGame);
    }

    public static Transaction createClaimChanceGameReward() {
        return createInstance(TransactionType.ClaimChanceGameReward);
    }

    public static Transaction createCrowdfundingActivity() {
        return createInstance(TransactionType.CrowdfundingActivity);
    }

    public static Transaction createCrowdfundingLottery() {
        return createInstance(TransactionType.CrowdfundingLottery);
    }

    public static Transaction createCrowdfundingClaim() {
        return createInstance(TransactionType.CrowdfundingClaim);
    }

    public static Transaction createCrowdfundingCancel() {
        return createInstance(TransactionType.CrowdfundingCancel);
    }

    public static Transaction createPresentedPoints() {
        return createInstance(TransactionType.PresentedPoints);
    }

    public static Transaction createPresentedPointsClaim() {
        return createInstance(TransactionType.PresentedPointsClaim);
    }

    public static Transaction createPresentedPointsCancel() {
        return createInstance(TransactionType.PresentedPointsCancel);
    }

    public ShopInfo createOrGetShopInfo() {
        if (this.shopInfo == null) {
            this.shopInfo = ShopInfo.createInstance();
        }

        return this.shopInfo;
    }

    public TagInfo createOrGetTagInfo() {
        if (this.tagInfo == null) {
            this.tagInfo = TagInfo.createInstance();
        }

        return this.tagInfo;
    }

    public SpentPoints createOrGetSpentPoints() {
        if (this.spentPoints == null) {
            this.spentPoints = SpentPoints.createInstance();
        }
        return this.spentPoints;
    }

    public GainPoints createOrGetGainPoints() {
        if (this.gainPoints == null) {
            this.gainPoints = GainPoints.createInstance();
        }

        return this.gainPoints;
    }

    @Override
    public void validate() throws PropertyInvalidException {
        super.validate();

        if (transactionType == 0 || transactionType < -1 || lotteryId == 0) {
            throw new PropertyInvalidException("Transaction property [transactionType] and [lotteryId] can't be less than or equals to zero.");
        }

        if (transactionType == TransactionType.PointLottery.getValue() && spentPoints != null) {
            spentPoints.validate();
        }

    }

    @Override
    public FlatTransaction getFlat() {
        FlatTransaction flat = new FlatTransaction();
        super.setFlat(flat);

        flat.setTt(transactionType);
        flat.setLi(lotteryId);

        if (this.spentPoints != null) {
            flat.setDpt(this.spentPoints.getPointType());
            flat.setDpd(this.spentPoints.getPointDetail());
        }

        if (this.gainPoints != null) {
            flat.setPtri(this.gainPoints.getPointRuleId());
            flat.setPts(this.gainPoints.getPointsAwarded());
        }

        if (this.shopInfo != null) {
            flat.setShi(this.shopInfo.getShopId());
        }

        return flat;
    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                super.toString() +
                (spentPoints != null ? spentPoints : "") +
                (gainPoints != null ? gainPoints : "") +
                (shopInfo != null ? shopInfo : "") +
                (tagInfo != null ? tagInfo : "") +
                ", tt=" + transactionType +
                ", li=" + lotteryId
                + "} ";
    }
}
