package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All open API log deserialize scheme
 *
 */
public abstract class OpenAPILogTransformer<U> extends KryoTransformer<FlatOpenAPILog,U> {

    public OpenAPILogTransformer() {
        super(FlatOpenAPILog.class);
    }

}
