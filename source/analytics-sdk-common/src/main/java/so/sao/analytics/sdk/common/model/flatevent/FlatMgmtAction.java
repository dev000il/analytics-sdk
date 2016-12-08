package so.sao.analytics.sdk.common.model.flatevent;

/**
 * An flag management action class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatMgmtAction extends FlatBasicEvent {

    private static final long serialVersionUID = 7267894947043875246L;

    // User Identifier
    private String pn;
    private String u;
    private String tu;
    private String wu;

    private String a;
    private String msg;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
        put("a", a);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        put("msg", msg);
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
        put("pn", pn);
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
        put("u", u);
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
        put("tu", tu);
    }

    public String getWu() {
        return wu;
    }

    public void setWu(String wu) {
        this.wu = wu;
        put("wu", wu);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
