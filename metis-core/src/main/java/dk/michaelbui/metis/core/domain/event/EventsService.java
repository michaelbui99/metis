package dk.michaelbui.metis.core.domain.event;

import com.fasterxml.jackson.databind.JsonNode;

public interface EventsService {
    Event eventFromTemplate(EventTemplate template, JsonNode input);
}
