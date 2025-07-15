package dk.michaelbui.metis.server.domain;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonSelector {
    private String selector;

    public JsonSelector() {
    }

    public JsonSelector(String selector) {
        this.selector = selector;
    }

    public Object apply(JsonNode node) {
        throw new RuntimeException("Not implemented yet");
    }
}
