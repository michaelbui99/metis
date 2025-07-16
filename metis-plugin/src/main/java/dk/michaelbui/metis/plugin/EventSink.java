package dk.michaelbui.metis.plugin;

import dk.michaelbui.metis.core.domain.event.Event;
import org.pf4j.ExtensionPoint;

import java.util.List;
import java.util.Map;

public interface EventSink extends ExtensionPoint {
    String getId();
    void initialize(Map<String, Object> params);
    void sendEvents(List<Event> event);
}
