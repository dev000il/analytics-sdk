package so.sao.analytics.sdk.kinesis.events;


import so.sao.analytics.sdk.common.model.flatevent.FlatAppSystem;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All application system deserialize scheme
 *
 */
public abstract class AppSystemTransformer<U> extends KryoTransformer<FlatAppSystem,U> {

    public AppSystemTransformer() {
        super(FlatAppSystem.class);
    }


}