package dk.michaelbui.metis.core.domain.condition;

import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

public class NotCondition extends Condition{
    private Object inner;

    public NotCondition() {
        // For marshalling / unmarshalling
    }

    public NotCondition(Object inner) {
        this.inner = inner;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        if (inner instanceof Condition condition) {
            return !condition.evaluate(context);
        }
        if (inner instanceof JsonSelector selector) {
            Object value = selector.apply(context.getInput());
            if (!(value instanceof Boolean)) {
                return false;
            }
            return !((Boolean) value);
        }
        return false;
    }

    public Object getInner() {
        return inner;
    }

    public void setInner(Object inner) {
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "NotCondition{" +
                "inner=" + inner +
                '}';
    }
}
