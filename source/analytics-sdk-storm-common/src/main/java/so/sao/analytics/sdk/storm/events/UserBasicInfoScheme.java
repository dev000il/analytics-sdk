package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatUserBasicInfo;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All activity deserialize scheme
 *
 * @author senhui.li
 */
public class UserBasicInfoScheme extends KryoScheme<FlatUserBasicInfo> {

    private static final long serialVersionUID = 1862749765895119235L;

    public UserBasicInfoScheme() {
        super(FlatUserBasicInfo.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("userbasicinfo");
    }

}