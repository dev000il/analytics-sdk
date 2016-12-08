package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.storm.common.KryoScheme;
import so.sao.analytics.sdk.common.model.flatevent.FlatThirdparty;

/**
 * All third party deserialize scheme
 *
 * @author senhui.li
 */
public class ThirdpartyScheme extends KryoScheme<FlatThirdparty> {

    private static final long serialVersionUID = -3243640794091418040L;

    public ThirdpartyScheme() {
        super(FlatThirdparty.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("thirdparty");
    }

}