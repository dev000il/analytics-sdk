package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All activity deserialize scheme
 *
 * @author senhui.li
 */
public class ActivityScheme extends KryoScheme<FlatActivity> {

    private static final long serialVersionUID = 1862749765895119235L;

    public ActivityScheme() {
        super(FlatActivity.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("activity");
    }

}