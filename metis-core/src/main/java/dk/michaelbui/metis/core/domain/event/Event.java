package dk.michaelbui.metis.core.domain.event;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public class Event {
    private UUID id;
    private String name;
    private Map<String, Object> params;
    private OffsetDateTime timestamp;

    public Event(UUID id, String name, Map<String, Object> params) {
        this.id = id;
        this.name = name;
        this.params = params;
    }

    public Event() {
    }

    public Event param(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
