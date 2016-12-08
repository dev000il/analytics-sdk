package so.sao.analytics.sdk.common.model;

import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Check Property values
 *
 * @author senhui.li
 */
public interface CheckProperty {

    /**
     * Validate required parameters
     * Here will throws an exception when invalid value
     */
    void validate() throws PropertyInvalidException;
}
