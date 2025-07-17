package dk.michaelbui.metis.core.domain.event;

import java.util.Map;

public class EventTemplate {
    private String eventName;
    private Map<String, ParamValue> params;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, ParamValue> getParams() {
        return params;
    }

    public void setParams(Map<String, ParamValue> params) {
        this.params = params;
    }
}
