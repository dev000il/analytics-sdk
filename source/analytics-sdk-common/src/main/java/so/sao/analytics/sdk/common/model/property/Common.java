package so.sao.analytics.sdk.common.model.property;

import java.util.Date;
import java.util.UUID;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;
import so.sao.analytics.sdk.util.DateUtil;

/**
 * Common Property
 *
 * @author senhui.li
 */
public class Common implements CheckProperty {

    /**
     * Unique request ID
     */
    private String requestId;
    /**
     * Schema Version
     */
    private int schemeVersion = 2;
    /**
     * Timestamp
     */
    private Date timestamp;
    /**
     * Company ID
     */
    private int companyId;

    public static Common createInstance() {
        Common common = new Common();
        common.requestId = UUID.randomUUID().toString().replaceAll("-", "");
        common.timestamp = DateUtil.getCurrentGMT8();
        return common;
    }

    @Override
    public void validate() throws PropertyInvalidException {
        if (companyId == 0) {
            throw new PropertyInvalidException("Common property [companyId] can't be equals to zero.");
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getSchemeVersion() {
        return schemeVersion;
    }

    public void setSchemeVersion(int schemeVersion) {
        this.schemeVersion = schemeVersion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "r='" + requestId + '\'' +
                ", lv=" + schemeVersion +
                ", ts='" + DateUtil.getDefDateFormat(timestamp) + '\'' +
                ", c=" + companyId;
    }
}
