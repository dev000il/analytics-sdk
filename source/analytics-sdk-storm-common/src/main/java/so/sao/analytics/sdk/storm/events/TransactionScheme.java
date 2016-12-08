package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatTransaction;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All transaction deserialize scheme
 *
 * @author senhui.li
 */
public class TransactionScheme extends KryoScheme<FlatTransaction> {

    private static final long serialVersionUID = 8207870103616394649L;

    public TransactionScheme() {
        super(FlatTransaction.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("transaction");
    }
}
