package dk.michaelbui.metis.core.domain.paramvalue;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.event.ParamValue;

public class LiteralValue implements ParamValue {
    private Object value;

    public LiteralValue() {
        // For marshalling / unmarshalling
    }

    public LiteralValue(Object value) {
        this.value = value;
    }

    @Override
    public Object apply(JsonNode input) {
        return value;
    }
}
