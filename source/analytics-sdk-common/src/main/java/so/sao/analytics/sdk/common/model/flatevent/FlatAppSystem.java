package so.sao.analytics.sdk.common.model.flatevent;

/**
 * An flag appsystem class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatAppSystem extends FlatBasicEvent {

    private static final long serialVersionUID = -1027597183576428829L;

    private int ae;
    private int hae;
    private String l;
    private String msg;

    public int getAe() {
        return ae;
    }

    public void setAe(int ae) {
        this.ae = ae;
        put("ae", ae);
    }

    public int getHae() {
        return hae;
    }

    public void setHae(int hae) {
        this.hae = hae;
        put("hae", hae);
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
        put("l", l);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        put("msg", msg);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
