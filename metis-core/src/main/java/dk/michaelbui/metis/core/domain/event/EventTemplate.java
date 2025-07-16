package dk.michaelbui.metis.core.domain.event;

import dk.michaelbui.metis.core.domain.selector.JsonSelector;

import java.util.Map;

public class EventTemplate {
    private String eventName;
    private Map<String, JsonSelector> params;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, JsonSelector> getParams() {
        return params;
    }

    public void setParams(Map<String, JsonSelector> params) {
        this.params = params;
    }
}
