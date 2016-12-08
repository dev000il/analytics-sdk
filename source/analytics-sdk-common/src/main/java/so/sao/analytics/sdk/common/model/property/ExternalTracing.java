package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * External Tracing Property
 *
 * @author senhui.li
 */
public class ExternalTracing implements CheckProperty {

    /**
     * Origin IP
     */
    private String originIp;
    /**
     * GeoJSON
     */
    private String geoJson;
    /**
     * User Agent
     */
    private String userAgent;

    public static ExternalTracing createInstance() {
        return new ExternalTracing();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setExternalTracing(String originIp, String geoJson, String userAgent) {
        setOriginIp(originIp);
        setGeoJson(geoJson);
        setUserAgent(userAgent);
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    public String getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(String geoJson) {
        this.geoJson = geoJson;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return ", oip='" + (originIp==null ? "" : originIp) + '\'' +
                ", geoip='" + (geoJson==null ? "" : geoJson) + '\'' +
                ", ua='" + (userAgent==null ? "" : userAgent) + '\'';
    }
}
