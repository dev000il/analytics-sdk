package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All open API log deserialize scheme
 *
 * @author senhui.li
 */
public class OpenAPILogScheme extends KryoScheme<FlatOpenAPILog> {

    private static final long serialVersionUID = -8902116099335918829L;

    public OpenAPILogScheme() {
        super(FlatOpenAPILog.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("openapilog");
    }
}
