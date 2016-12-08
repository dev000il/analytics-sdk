package so.sao.analytics.sdk.common.model.flatevent;

import java.util.Map;

/**
 * An flag transaction class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatTransaction extends FlatBasicReward {

    private static final long serialVersionUID = 5212560013407124669L;

    // Transaction
    private int tt;

    // Point Lottery
    private int li;

    // Spent Points
    private int dpt;
    private int dpd;

    // Gain Points
    private int ptri;
    private Map<String, Integer> pts;

    // Shop
    private long shi;

    // Tag Info
    private String hid;
    private String num;
    private String bid;
    private int did;
    private String orn;
    private long sdid;

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
        put("tt", tt);
    }

    public int getLi() {
        return li;
    }

    public void setLi(int li) {
        this.li = li;
        put("li", li);
    }

    public int getDpt() {
        return dpt;
    }

    public void setDpt(int dpt) {
        this.dpt = dpt;
        put("dpt", dpt);
    }

    public int getDpd() {
        return dpd;
    }

    public void setDpd(int dpd) {
        this.dpd = dpd;
        put("dpd", dpd);
    }

    public long getShi() {
        return shi;
    }

    public void setShi(long shi) {
        this.shi = shi;
        put("shi", shi);
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
        put("hid", hid);
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
        put("num", num);
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
        put("bid", bid);
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
        put("did", did);
    }

    public String getOrn() {
        return orn;
    }

    public void setOrn(String orn) {
        this.orn = orn;
        put("orn", orn);
    }

    public long getSdid() {
        return sdid;
    }

    public void setSdid(long sdid) {
        this.sdid = sdid;
        put("sdid", sdid);
    }

    public int getPtri() {
        return ptri;
    }

    public void setPtri(int ptri) {
        this.ptri = ptri;
        put("ptri", ptri);
    }

    public Map<String, Integer> getPts() {
        return pts;
    }

    public void setPts(Map<String, Integer> pts) {
        this.pts = pts;
        put("pts", pts);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
