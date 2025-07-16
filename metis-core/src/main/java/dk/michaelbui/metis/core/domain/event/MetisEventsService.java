package dk.michaelbui.metis.core.domain.event;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.selector.JsonSelector;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class MetisEventsService implements EventsService {
    @Override
    public Event eventFromTemplate(EventTemplate template, JsonNode input) {
        Map<String, JsonSelector> paramTemplate = template.getParams();
        Map<String, Object> params = paramTemplate.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey
                                , entry -> entry.getValue().apply(input)
                        )
                );

        Event event = new Event(UUID.randomUUID(), template.getEventName(), params);
        event.setTimestamp(OffsetDateTime.now());
        return event;
    }
}
