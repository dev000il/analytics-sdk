package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.common.model.property.AdditionalMetadata;
import so.sao.analytics.sdk.common.model.property.Common;
import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.common.model.property.ExternalTracing;
import so.sao.analytics.sdk.exception.PropertyInvalidException;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

/**
 * BasicEvent super class
 *
 * @author senhui.li
 */
public abstract class BasicEvent implements CheckProperty {

    // Properties
    protected Common common = Common.createInstance();
    protected InternalTracing internalTrace = InternalTracing.createInstance();
    protected ExternalTracing externalTrace;
    protected AdditionalMetadata addMetadata;

    public Common createOrGetCommon() {
        if (this.common == null) {
            this.common = Common.createInstance();
        }

        return this.common;
    }

    public InternalTracing createOrGetInternalTracing() {
        if (this.internalTrace == null) {
            this.internalTrace = InternalTracing.createInstance();
        }

        return this.internalTrace;
    }

    public ExternalTracing createOrGetExternalTracing() {
        if (this.externalTrace == null) {
            this.externalTrace = ExternalTracing.createInstance();
        }

        return this.externalTrace;
    }

    public AdditionalMetadata createOrGetAddMetadata() {
        if (this.addMetadata == null) {
            this.addMetadata = AdditionalMetadata.createInstance();
        }

        return this.addMetadata;
    }

    @Override
    public void validate() throws PropertyInvalidException {

        common.validate();

        internalTrace.validate();

    }

    public abstract FlatBasicEvent getFlat();

    protected void setFlat(FlatBasicEvent flat) {
        flat.setCommon(this.common);
        flat.setInternal(this.internalTrace);
        flat.setExternal(this.externalTrace);
        flat.setAdditionalMetadata(this.addMetadata);
    }

    public void setCompanyId(int companyId) {
        this.common.setCompanyId(companyId);
    }

    @Override
    public String toString() {
        return "" +
                common +
                internalTrace +
                (externalTrace != null ? externalTrace : "") +
                (addMetadata != null ? addMetadata : "");
    }
}
