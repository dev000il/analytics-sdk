package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.common.model.flatevent.FlatInvalidMessage;

/**
 * Collect invalid message from all event
 *
 * @author senhui.li
 */
public class InvalidMessage extends BasicEvent {

    private String errorMessage;

    public static InvalidMessage createInstance() {
        InvalidMessage event = new InvalidMessage();
        event.addMetadata = event.createOrGetAddMetadata();
        return event;
    }

    public void setEventType(String eventType) {
        this.addMetadata.setEventType(eventType);
    }

    public void setFlatEventSource(String flatEventSource) {
        this.addMetadata.setFlatEventSource(flatEventSource);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public FlatBasicEvent getFlat() {
        FlatInvalidMessage flat = new FlatInvalidMessage();
        super.setFlat(flat);

        return flat;
    }

    @Override
    public String toString() {
        return "InvalidMessage{" +
                super.toString() +
                '}';
    }
}
