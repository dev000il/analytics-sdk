package so.sao.analytics.sdk.common.enums;

/**
 * Third party type
 *
 * @author senhui.li
 */
public enum ThirdpartyType {

    LiuSha(1),
    MobileBandwidthTopUp(2),
    UserRegisterTarget(3),
    // these type value all sync with platform
    // so need ask platform when you update this type.
    MengTuoClaim(8),
    MengTuoCancel(9);

    private int type;

    ThirdpartyType(int type) {
        this.type = type;
    }

    public int getVlaue() {
        return this.type;
    }
}
