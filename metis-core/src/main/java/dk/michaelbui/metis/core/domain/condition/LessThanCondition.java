package dk.michaelbui.metis.core.domain.condition;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

/**
 * <p>Represents a less than (or equal to) condition such as 5 < 2 or 5<=2</p>
 * <p>The condition is represented as SELECTOR < RIGHT_OPERAND</p>
 */
public class LessThanCondition extends Condition {
    /**
     * Used to select the left operand
     * */
    private JsonSelector selector;

    /**
     * Right operand
     * */

    private Number rightOperand;

    /**
     * Modifies whether it is a < condition (when false) or <= condition (when true)
     * */
    private boolean allowEquals;

    public LessThanCondition() {
        // For marshalling / unmarshalling
    }

    public LessThanCondition(JsonSelector selector, Number rightOperand, boolean allowEquals) {
        this.selector = selector;
        this.rightOperand = rightOperand;
        this.allowEquals = allowEquals;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        JsonNode input = context.getInput();
        if (input == null || input.isNull()) {
            return false;
        }

        Object leftOperand = selector.apply(input);
        switch (leftOperand) {
            case Number number -> {
                if (allowEquals) {
                    return number.doubleValue() <= rightOperand.doubleValue();
                } else {
                    return number.doubleValue() < rightOperand.doubleValue();
                }
            }
            case String string -> {
                if (allowEquals) {
                    return string.length() <= rightOperand.intValue();
                } else {
                    return string.length() < rightOperand.intValue();
                }
            }
            default -> {
                return false;
            }
        }
    }

    public JsonSelector getSelector() {
        return selector;
    }

    public void setSelector(JsonSelector selector) {
        this.selector = selector;
    }

    public Number getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Number rightOperand) {
        this.rightOperand = rightOperand;
    }

    public boolean isAllowEquals() {
        return allowEquals;
    }

    public void setAllowEquals(boolean allowEquals) {
        this.allowEquals = allowEquals;
    }

    @Override
    public String toString() {
        return "LessThanCondition{" +
                "selector=" + selector +
                ", rightOperand=" + rightOperand +
                ", allowEquals=" + allowEquals +
                '}';
    }
}
