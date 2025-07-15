package dk.michaelbui.metis.server.domain.event;

import java.util.Map;
import java.util.UUID;

public class Event {
    private final UUID id;
    private final String name;
    private Map<String, Object> params;

    public Event(UUID id, String name, Map<String, Object> params) {
        this.id = id;
        this.name = name;
        this.params = params;
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
}
