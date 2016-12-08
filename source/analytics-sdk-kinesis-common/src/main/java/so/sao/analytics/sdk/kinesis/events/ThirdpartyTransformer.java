package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatThirdparty;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All third party deserialize scheme
 *
 */
public abstract class ThirdpartyTransformer<U> extends KryoTransformer<FlatThirdparty,U> {

    public ThirdpartyTransformer() {
        super(FlatThirdparty.class);
    }

}