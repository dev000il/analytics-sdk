package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;


/**
 * All activity deserialize scheme
 *
 */
public abstract class ActivityTransformer<U> extends KryoTransformer<FlatActivity, U> {

    public ActivityTransformer() {
        super(FlatActivity.class);
    }

}