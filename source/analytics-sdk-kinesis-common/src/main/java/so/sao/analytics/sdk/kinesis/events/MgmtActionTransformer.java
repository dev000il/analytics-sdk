package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatMgmtAction;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All management deserialize scheme
 *
 */
public abstract class MgmtActionTransformer<U> extends KryoTransformer<FlatMgmtAction,U> {

    public MgmtActionTransformer() {
        super(FlatMgmtAction.class);
    }

}
