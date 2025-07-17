package dk.michaelbui.metis.core.domain.paramvalue;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.event.ParamValue;
import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

public class BindingValue implements ParamValue {
    private String alias;
    private JsonSelector selector;

    public BindingValue() {
        // For marshalling / unmarshalling
    }

    public BindingValue(String alias, JsonSelector selector) {
        this.alias = alias;
        this.selector = selector;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JsonSelector getSelector() {
        return selector;
    }

    public void setSelector(JsonSelector selector) {
        this.selector = selector;
    }

    @Override
    public Object apply(JsonNode input) {
        if (selector == null) {
            return null;
        }

        return selector.apply(input);
    }
}
