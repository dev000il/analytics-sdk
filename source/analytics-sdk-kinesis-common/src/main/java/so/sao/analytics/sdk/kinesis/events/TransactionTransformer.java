package so.sao.analytics.sdk.kinesis.events;

import so.sao.analytics.sdk.common.model.flatevent.FlatTransaction;
import so.sao.analytics.sdk.kinesis.common.KryoTransformer;

/**
 * All transaction deserialize scheme
 *
 */
public abstract class TransactionTransformer<U> extends KryoTransformer<FlatTransaction,U> {

    public TransactionTransformer() {
        super(FlatTransaction.class);
    }

}
