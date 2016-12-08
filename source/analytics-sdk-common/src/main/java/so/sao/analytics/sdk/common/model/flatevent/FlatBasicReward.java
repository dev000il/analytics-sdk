package so.sao.analytics.sdk.common.model.flatevent;

import java.util.HashMap;
import java.util.Map;

import so.sao.analytics.sdk.common.model.property.*;

public class FlatBasicReward extends FlatBasicEvent {

    private static final long serialVersionUID = 1043825544130481471L;

    // User Identifier
    protected String pn;
    protected String u;
    protected String tu;
    protected String wu;

    // Win Rewards
    private long wid;
    private int rid;
    private String rt;
    private String ra;
    private Map<String, String> rwds = new HashMap<>();

    // Monetary Reward Info
    private String vid;
    private String bkn;
    private String bkhn;
    private String bkhi;
    private String bkht;
    private String bkc;

    // Physical Reward Info
    private String srn;
    private String spn;
    private String sp;
    private String sc;
    private String sa;
    private String sic;

    // Action Result
    private int okid;
    private String ok;

    public void setUser(UserIdentifier user) {
        if (user != null) {
            setPn(user.getPhoneNumber());
            setU(user.getUserId());
            setTu(user.getTaobaoUserId());
            setWu(user.getWechatUserId());
        }
    }

    public void setWinReward(WinRewards winRewards) {
        if (winRewards != null) {
            setWid(winRewards.getWinnerId());
            setRid(winRewards.getRewardId());
            setRt(winRewards.getRewardType());
            setRa(winRewards.getRewardAmount());
            setRwds(winRewards.getRewardAdwarded());
        }
    }

    public void setMonetaryReward(MonetaryRewardInfo monetaryRewardInfo) {
        if (monetaryRewardInfo != null) {
            setVid(monetaryRewardInfo.getVendorId());
            setBkn(monetaryRewardInfo.getBankActNumber());
            setBkhn(monetaryRewardInfo.getBankActHolderName());
            setBkhi(monetaryRewardInfo.getBankActHolderId());
            setBkht(monetaryRewardInfo.getBankActHolderIdType());
            setBkc(monetaryRewardInfo.getBankCode());
        }
    }

    public void setPhysicalReward(PhysicalRewardInfo physicalRewardInfo) {
        if (physicalRewardInfo != null) {
            setSrn(physicalRewardInfo.getShipRecipientName());
            setSpn(physicalRewardInfo.getShipPhoneNumber());
            setSp(physicalRewardInfo.getShipProvince());
            setSc(physicalRewardInfo.getShipCity());
            setSa(physicalRewardInfo.getShipAddress());
            setSic(physicalRewardInfo.getShipIdCard());
        }
    }

    public void setActionResult(ActionResult actionResult) {
        if (actionResult != null) {
            setOkid(actionResult.getSuccessStatus());
            setOk(actionResult.getSuccessMessage());
        }
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

    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
        put("rid", rid);
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
        put("rt", rt);
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
        put("ra", ra);
    }

    public Map<String, String> getRwds() {
        return rwds;
    }

    public void setRwds(Map<String, String> rwds) {
        this.rwds.putAll(rwds);
        put("rwds", rwds);
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
        put("vid", vid);
    }

    public String getBkn() {
        return bkn;
    }

    public void setBkn(String bkn) {
        this.bkn = bkn;
        put("bkn", bkn);
    }

    public String getBkhn() {
        return bkhn;
    }

    public void setBkhn(String bkhn) {
        this.bkhn = bkhn;
        put("bkhn", bkhn);
    }

    public String getBkhi() {
        return bkhi;
    }

    public void setBkhi(String bkhi) {
        this.bkhi = bkhi;
        put("bkhi", bkhi);
    }

    public String getBkht() {
        return bkht;
    }

    public void setBkht(String bkht) {
        this.bkht = bkht;
        put("bkht", bkht);
    }

    public String getBkc() {
        return bkc;
    }

    public void setBkc(String bkc) {
        this.bkc = bkc;
        put("bkc", bkc);
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
        put("srn", srn);
    }

    public String getSpn() {
        return spn;
    }

    public void setSpn(String spn) {
        this.spn = spn;
        put("spn", spn);
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
        put("sp", sp);
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
        put("sc", sc);
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
        put("sa", sa);
    }

    public String getSic() {
        return sic;
    }

    public void setSic(String sic) {
        this.sic = sic;
        put("sic", sic);
    }

    public int getOkid() {
        return okid;
    }

    public void setOkid(int okid) {
        this.okid = okid;
        put("okid", okid);
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
        put("ok", ok);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
