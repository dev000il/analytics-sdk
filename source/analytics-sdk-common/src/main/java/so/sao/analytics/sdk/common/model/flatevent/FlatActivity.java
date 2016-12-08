package so.sao.analytics.sdk.common.model.flatevent;

import java.util.HashMap;
import java.util.Map;

/**
 * An flag activity class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatActivity extends FlatBasicReward {

    private static final long serialVersionUID = 2730338878009063870L;

    // Activity type
    private int at;

    // Product Info
    private int pid;
    private String sid;

    // Tag Info
    private String hid;
    private String num;
    private String bid;
    private int did;
    private String orn;
    private long sdid;

    // Promotion Info
    private int pri;
    private boolean pnu;

    // Gain Points
    private int ptri;
    private Map<String, Integer> pts = new HashMap<>();

    // Shop
    private long shi;

    // Sharing
    private int si;
    private int st;

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
        put("at", at);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
        put("pid", pid);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
        put("sid", sid);
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

    public int getPri() {
        return pri;
    }

    public void setPri(int pri) {
        this.pri = pri;
        put("pri", pri);
    }

    public boolean isPnu() {
        return pnu;
    }

    public void setPnu(boolean pnu) {
        this.pnu = pnu;
        put("pnu", pnu);
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
        if (pts != null) {
            this.pts.putAll(pts);
            put("pts", this.pts);
        }
    }

    public long getShi() {
        return shi;
    }

    public void setShi(long shi) {
        this.shi = shi;
        put("shi", shi);
    }

    public int getSi() {
        return si;
    }

    public void setSi(int si) {
        this.si = si;
        put("si", si);
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
        put("st", st);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
