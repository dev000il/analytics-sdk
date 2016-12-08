package so.sao.analytics.sdk.common.model.flatevent;

/**
 * Flat InvalidMessage object
 *
 * @author senhui.li
 */
public class FlatInvalidMessage extends FlatBasicEvent {

    private static final long serialVersionUID = -5992928222613169553L;

    private String errmsg;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        put("errmsg", errmsg);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
