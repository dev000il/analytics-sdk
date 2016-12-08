package so.sao.analytics.sdk.common.model.flatevent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import so.sao.analytics.sdk.common.model.property.AdditionalMetadata;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.common.model.property.InternalTracing;
import so.sao.analytics.sdk.util.DateUtil;

/**
 * The common event by custom with the common, internal tracing, external
 * tracing, user identifier, addition meta data property.
 *
 * @author senhui.li
 */
public class FlatBasicEvent implements Serializable {

    private static final long serialVersionUID = 4095060510394630017L;

    private Map<String, Object> data = new HashMap<>();

    // Common property
    protected String r;
    protected int lv;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    protected Date ts;
    protected int c;
    // Internal Tracing
    protected String d;
    protected String h;
    protected String v;
    protected String cl;
    // External Tracing
    protected String oip;
    protected String geoip;
    protected String ua;

    // Add Meta data
    private String ipl;
    // All supp fields
    private Map<String, Object> supp = new HashMap<>();

    public void put(String key, Object value) {
        this.data.put(key, value);
    }

    public void setCommon(Common common) {
        setR(common.getRequestId());
        setLv(common.getSchemeVersion());
        setTs(common.getTimestamp());
        setC(common.getCompanyId());
    }

    public void setInternal(InternalTracing internal) {
        setD(internal.getServiceDeployment());
        setH(internal.getHostInstance());
        setV(internal.getHostInstanceVer());
        setCl(internal.getClientId());
    }

    public void setExternal(ExternalTracing external) {
        if (external != null) {
            setOip(external.getOriginIp());
            setGeoip(external.getGeoJson());
            setUa(external.getUserAgent());
        }
    }

    public void setAdditionalMetadata(AdditionalMetadata addMetadata) {
        if (addMetadata != null) {
            setIpl(addMetadata.getIdentifyPublicLabel());
            setSupp(addMetadata.getSupplementaryFiled());
        }
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
        put("r", r);
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
        put("lv", lv);
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
        put("ts", DateUtil.getDefDateFormat(ts));
    }

    public void setTs(String ts) {
        this.ts = DateUtil.getDefDateParse(ts);
        put("ts", ts);
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
        put("c", c);
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
        put("d", d);
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
        put("h", h);
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
        put("v", v);
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
        put("cl", cl);
    }

    public String getOip() {
        return oip;
    }

    public void setOip(String oip) {
        this.oip = oip;
        put("oip", oip);
    }

    public String getGeoip() {
        return geoip;
    }

    public void setGeoip(String geoip) {
        this.geoip = geoip;
        put("geoip", geoip);
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
        put("ua", ua);
    }

    public String getIpl() {
        return ipl;
    }

    public void setIpl(String ipl) {
        this.ipl = ipl;
        put("ipl", ipl);
    }

    public Map<String, Object> getSupp() {
        return supp;
    }

    public void setSupp(Map<String, Object> supp) {
        if (supp != null && !supp.isEmpty()) {
            this.supp.putAll(supp);
            put("supp", this.supp);
        }
    }

    public Map<String, Object> getAll() {
        return this.data;
    }
}
