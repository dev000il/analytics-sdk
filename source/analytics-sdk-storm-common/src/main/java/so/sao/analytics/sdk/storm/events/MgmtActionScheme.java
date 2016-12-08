package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatMgmtAction;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All management deserialize scheme
 *
 * @author senhui.li
 */
public class MgmtActionScheme extends KryoScheme<FlatMgmtAction> {

    private static final long serialVersionUID = 502429731789678658L;

    public MgmtActionScheme() {
        super(FlatMgmtAction.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("mgmtaction");
    }
}
