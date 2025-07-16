package dk.michaelbui.metis.plugin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MetisPluginsConfiguration {
    @JsonProperty("event_sinks")
    private Map<String, EventSinkConfiguration> eventSinks;

    public MetisPluginsConfiguration() {
        // For marshalling / unmarshalling
    }

    public Map<String, EventSinkConfiguration> getEventSinks() {
        return eventSinks;
    }

    public void setEventSinks(Map<String, EventSinkConfiguration> eventSinks) {
        this.eventSinks = eventSinks;
    }
}
