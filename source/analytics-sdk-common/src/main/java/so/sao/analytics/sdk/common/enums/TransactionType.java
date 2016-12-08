package so.sao.analytics.sdk.common.enums;

/**
 * Transaction type
 *
 * @author senhui.li
 */
public enum TransactionType {

    Unknown(-1),
    ExchangeMallReward(1),
    PointGame(2),
    ClaimPointGameReward(3),
    ClaimPointLotteryReward(4),
    PointLottery(5),
    ChanceGame(6),
    ClaimChanceGameReward(7),
    CrowdfundingActivity(8),
    CrowdfundingLottery(9),
    CrowdfundingClaim(10),
    CrowdfundingCancel(11),
    PresentedPoints(12),
    PresentedPointsClaim(13),
    PresentedPointsCancel(14);

    private int type;

    TransactionType(int type) {
        this.type = type;
    }

    public int getValue() {
        return this.type;
    }
}
