package so.sao.analytics.sdk.common.enums;

/**
 * Activity Type
 *
 * @author senhui.li
 */
public enum ActivityType {

    ScanCode(1), EnterLottery(2), ClaimReward(3), Sharing(4);

    private int type;

    ActivityType(int type) {
        this.type = type;
    }

    public int getValue() {
        return this.type;
    }
}
