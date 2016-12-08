package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatUserBasicInfo;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All activity deserialize scheme
 *
 */
public abstract class UserBasicInfoTransformer<U> extends KryoTransformer<FlatUserBasicInfo,U> {

    public UserBasicInfoTransformer() {
        super(FlatUserBasicInfo.class);
    }

}