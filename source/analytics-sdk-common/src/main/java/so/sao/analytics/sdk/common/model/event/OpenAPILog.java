package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Our open API log
 *
 * @author senhui.li
 */
public class OpenAPILog extends BasicEvent {

    private String requestUrl;

    private String parameterStr;

    private String ISV;

    private String responseStr;

    private boolean isException;

    private String exceptionType;

    private String exceptionCode;

    private String exceptionMesg;

    public static OpenAPILog createInstance() {
        return new OpenAPILog();
    }

    @Override
    public void validate() throws PropertyInvalidException {

        super.validate();

        if (requestUrl == null || requestUrl.isEmpty()) {
            throw new PropertyInvalidException("OpenAPILog property [requestUrl] can't be NULL or empty.");
        }

    }

    @Override
    public FlatBasicEvent getFlat() {
        FlatOpenAPILog flat = new FlatOpenAPILog();
        super.setFlat(flat);

        flat.setRu(this.requestUrl);
        flat.setPs(this.parameterStr);
        flat.setRp(this.responseStr);
        flat.setIsv(this.ISV);
        flat.setIe(this.isException);
        flat.setEt(this.exceptionType);
        flat.setEc(this.exceptionCode);
        flat.setEm(this.exceptionMesg);

        return flat;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getParameterStr() {
        return parameterStr;
    }

    public void setParameterStr(String parameterStr) {
        this.parameterStr = parameterStr;
    }

    public String getISV() {
        return ISV;
    }

    public void setISV(String ISV) {
        this.ISV = ISV;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionMesg() {
        return exceptionMesg;
    }

    public void setExceptionMesg(String exceptionMesg) {
        this.exceptionMesg = exceptionMesg;
    }

    @Override
    public String toString() {
        return "OpenAPILog{" +
                super.toString() +
                ", ru='" + requestUrl + '\'' +
                ", ps='" + parameterStr + '\'' +
                ", isv='" + ISV + '\'' +
                ", rp='" + responseStr + '\'' +
                ", ie=" + isException +
                ", et='" + exceptionType + '\'' +
                ", ec='" + exceptionCode + '\'' +
                ", em='" + exceptionMesg + '\'' +
                "} ";
    }
}
