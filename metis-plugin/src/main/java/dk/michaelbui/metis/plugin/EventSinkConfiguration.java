package dk.michaelbui.metis.plugin;

import java.util.Map;

public class EventSinkConfiguration {
    private Map<String, Object> params;

    public EventSinkConfiguration() {
        // For marshalling / unmarshalling
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
