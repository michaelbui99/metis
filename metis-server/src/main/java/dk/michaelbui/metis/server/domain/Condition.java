package dk.michaelbui.metis.server.domain;

import com.fasterxml.jackson.databind.JsonNode;

public interface Condition {
    /**
     * Evaluate the condition with a JSON node as input
     *
     * @param node JSON node to evaluate the condition against
     * */
    boolean evaluate(JsonNode node);
}
