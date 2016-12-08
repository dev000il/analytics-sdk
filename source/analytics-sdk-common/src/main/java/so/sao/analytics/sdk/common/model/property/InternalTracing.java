package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;
import so.sao.analytics.sdk.util.SysInfoUtil;

/**
 * Internal Tracing Property
 *
 * @author senhui.li
 */
public class InternalTracing implements CheckProperty {

    /**
     * Service Deployment
     */
    private String serviceDeployment;
    /**
     * Host Instance
     */
    private String hostInstance;
    /**
     * Host Instance Version
     */
    private String hostInstanceVer;
    /**
     * Client ID
     */
    private String clientId;

    public static InternalTracing createInstance() {
        InternalTracing it = new InternalTracing();
        it.setHostInstance(SysInfoUtil.getHostName());
        return it;
    }

    @Override
    public void validate() throws PropertyInvalidException {
        if (serviceDeployment == null || serviceDeployment.isEmpty()
                || hostInstanceVer == null || hostInstanceVer.isEmpty()
                || clientId == null || clientId.isEmpty()) {
            throw new PropertyInvalidException("InternalTracing property [serviceDeployment],[clientId] and [hostInstanceVer] can't be NULL or empty.");
        }
    }

    public void setInternalTracing(String serviceDeployment, String hostInstance) {
        setServiceDeployment(serviceDeployment);
        setHostInstance(hostInstance);
    }

    public String getServiceDeployment() {
        return serviceDeployment;
    }

    public void setServiceDeployment(String serviceDeployment) {
        this.serviceDeployment = serviceDeployment;
    }

    public String getHostInstance() {
        return hostInstance;
    }

    private void setHostInstance(String hostInstance) {
        this.hostInstance = hostInstance;
    }

    public void copyHostInstance(String hostInstance) {
        this.hostInstance = hostInstance;
    }

    public String getHostInstanceVer() {
        return hostInstanceVer;
    }

    public void setHostInstanceVer(String hostInstanceVer) {
        this.hostInstanceVer = hostInstanceVer;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return ", d='" + serviceDeployment + '\'' +
                ", h='" + hostInstance + '\'' +
                ", v='" + hostInstanceVer + '\'' +
                ", cl='" + clientId + '\'';
    }
}
