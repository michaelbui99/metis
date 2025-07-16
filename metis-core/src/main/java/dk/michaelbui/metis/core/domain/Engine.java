package dk.michaelbui.metis.core.domain;

import dk.michaelbui.metis.core.domain.event.Event;

import java.util.List;

public interface Engine {
    /**
     * Takes a JSON string as input and {@link Event} based on the user defined rules.
     * */
    List<Event> input(String jsonString);
}
