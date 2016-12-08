package so.sao.analytics.sdk.common.model.flatevent;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import so.sao.analytics.sdk.util.DateUtil;

/**
 * An flag user basic info class this design for where need fast use
 *
 * @author senhui.li
 */
public class FlatUserBasicInfo extends FlatBasicEvent {

    private static final long serialVersionUID = -5287232942017907049L;

    // User Identifier
    private String pn;
    private String u;
    private String tu;
    private String wu;

    private String nkn;
    private int sex;
    private int age;
    @JSONField(format = "yyyy-MM-dd")
    private Date bd;
    private String aiu;
    private String cyn;
    private String pron;
    private String cin;
    private String area;
    private String dadr;
    private String eadr;

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

    public String getNkn() {
        return nkn;
    }

    public void setNkn(String nkn) {
        this.nkn = nkn;
        put("nkn", nkn);
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
        put("sex", sex);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        put("age", age);
    }

    public Date getBd() {
        return bd;
    }

    public void setBd(Date bd) {
        this.bd = bd;
        put("bd", DateUtil.getDefShortDateFormat(bd));
    }

    public void setBd(String bd) {
        this.bd = DateUtil.getDefShortDateParse(bd);
        put("bd", bd);
    }

    public String getAiu() {
        return aiu;
    }

    public void setAiu(String aiu) {
        this.aiu = aiu;
        put("aiu", aiu);
    }

    public String getCyn() {
        return cyn;
    }

    public void setCyn(String cyn) {
        this.cyn = cyn;
        put("cyn", cyn);
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
        put("pron", pron);
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
        put("cin", cin);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
        put("area", area);
    }

    public String getDadr() {
        return dadr;
    }

    public void setDadr(String dadr) {
        this.dadr = dadr;
        put("dadr", dadr);
    }

    public String getEadr() {
        return eadr;
    }

    public void setEadr(String eadr) {
        this.eadr = eadr;
        put("eadr", eadr);
    }

    @Override
    public String toString() {
        return getAll().toString();
    }
}
