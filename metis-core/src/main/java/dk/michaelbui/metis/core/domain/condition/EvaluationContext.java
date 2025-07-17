package dk.michaelbui.metis.core.domain.condition;

import com.fasterxml.jackson.databind.JsonNode;

public class EvaluationContext {
    private JsonNode input;

    public EvaluationContext() {
        // For marshalling / unmarshalling
    }

    public EvaluationContext(JsonNode input) {
        this.input = input;
    }


    public JsonNode getInput() {
        return input;
    }

    public void setInput(JsonNode input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "EvaluationContext{" +
                "input=" + input.toPrettyString() +
                '}';
    }
}
