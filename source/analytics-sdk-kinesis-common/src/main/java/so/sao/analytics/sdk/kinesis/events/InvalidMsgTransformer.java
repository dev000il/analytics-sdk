package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatInvalidMessage;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All invalid message deserialize scheme
 *
 */
public abstract class InvalidMsgTransformer<U> extends KryoTransformer<FlatInvalidMessage,U> {

    public InvalidMsgTransformer() {
        super(FlatInvalidMessage.class);
    }

}