package so.sao.analytics.sdk.common.model.flatevent;

/**
 * Flat open API log
 *
 * @author senhui.li
 */
public class FlatOpenAPILog extends FlatBasicEvent {

    private static final long serialVersionUID = -8650370373804706103L;

    private String ru;
    private String ps;
    private String isv;
    private String rp;
    private boolean ie;
    private String et;
    private String ec;
    private String em;

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
        put("ru", ru);
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
        put("ps", ps);
    }

    public String getIsv() {
        return isv;
    }

    public void setIsv(String isv) {
        this.isv = isv;
        put("isv", isv);
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
        put("rp", rp);
    }

    public boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
        put("ie", ie);
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
        put("et", et);
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
        put("ec", ec);
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
        put("em", em);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
