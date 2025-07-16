package dk.michaelbui.metis.server.domain;

import dk.michaelbui.metis.server.domain.event.Event;

import java.util.List;

public interface Engine {
    /**
     * Takes a JSON string as input and {@link Event} based on the user defined rules.
     * */
    List<Event> input(String jsonString);
}
