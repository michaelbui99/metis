package dk.michaelbui.metis.core.domain.rule;

import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

import java.util.HashMap;
import java.util.Map;

public class RuleContext {
    private Map<String, JsonSelector> bindings;

    public RuleContext() {
        this.bindings = new HashMap<>();
    }

    public void putBinding(String name, JsonSelector selector) {
        bindings.put(name, selector);
    }

    public Map<String, JsonSelector> getBindings() {
        return bindings;
    }

    @Override
    public String toString() {
        return "RuleContext{" +
                "bindings=" + bindings +
                '}';
    }
}
