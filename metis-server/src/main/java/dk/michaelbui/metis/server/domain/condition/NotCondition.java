package dk.michaelbui.metis.server.domain.condition;

import dk.michaelbui.metis.server.domain.selector.JsonSelector;

public class NotCondition extends Condition{
    private final Object inner;

    public NotCondition(Object inner) {
        this.inner = inner;
    }

    @Override
    public boolean evaluate() {
        if (inner instanceof Condition condition) {
            return !condition.evaluate();
        }
        if (inner instanceof JsonSelector selector) {
            Object value = selector.apply(getContext().getInput());
            if (!(value instanceof Boolean)) {
                return false;
            }
            return !((Boolean) value);
        }
        return false;
    }

    @Override
    public String toString() {
        return "NotCondition{" +
                "inner=" + inner +
                '}';
    }
}
