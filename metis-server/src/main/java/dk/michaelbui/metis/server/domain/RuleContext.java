package dk.michaelbui.metis.server.domain;

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
}
