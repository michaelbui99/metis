package dk.michaelbui.metis.core.domain.event;

import com.fasterxml.jackson.databind.JsonNode;

public interface ParamValue {
    Object apply(JsonNode input);
}
