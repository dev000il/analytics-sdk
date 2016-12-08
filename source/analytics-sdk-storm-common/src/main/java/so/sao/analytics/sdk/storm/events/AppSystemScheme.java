package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatAppSystem;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All application system deserialize scheme
 *
 * @author senhui.li
 */
public class AppSystemScheme extends KryoScheme<FlatAppSystem> {

    private static final long serialVersionUID = 3408773601790033053L;

    public AppSystemScheme() {
        super(FlatAppSystem.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("appsystem");
    }

}