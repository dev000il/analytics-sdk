package so.sao.analytics.sdk.storm.events;

import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.common.model.flatevent.FlatInvalidMessage;
import so.sao.analytics.sdk.storm.common.KryoScheme;

/**
 * All invalid message deserialize scheme
 *
 * @author senhui.li
 */
public class InvalidMsgScheme extends KryoScheme<FlatInvalidMessage> {

    private static final long serialVersionUID = 186277157895119235L;

    public InvalidMsgScheme() {
        super(FlatInvalidMessage.class);
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("invalidmsg");
    }

}