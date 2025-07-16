package dk.michaelbui.metis.server.domain.event;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.server.domain.selector.JsonSelector;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MetisEventsService implements EventsService {
    @Override
    public Event eventFromTemplate(EventTemplate template, JsonNode input) {
        Map<String, JsonSelector> paramTemplate = template.getParams();
        Map<String, Object> params = paramTemplate.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey
                                ,entry -> entry.getValue().apply(input)
                        )
                );

        Event event = new Event(UUID.randomUUID(), template.getEventName(), params);
        event.setTimestamp(OffsetDateTime.now());
        return event;
    }
}
