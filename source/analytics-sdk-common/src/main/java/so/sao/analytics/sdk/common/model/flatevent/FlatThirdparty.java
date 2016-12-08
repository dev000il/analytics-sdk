package so.sao.analytics.sdk.common.model.flatevent;

/**
 * An flag third party class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatThirdparty extends FlatBasicReward {

    private static final long serialVersionUID = -3269020377316825544L;

    private int tpt;

    public int getTpt() {
        return tpt;
    }

    public void setTpt(int tpt) {
        this.tpt = tpt;
        put("tpt", tpt);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
