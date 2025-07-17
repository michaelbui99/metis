package dk.michaelbui.metis.core.domain.condition;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

import java.util.Objects;

/**
 * <p>Represents a not equals condition such as "TEST" != "jest" or 2 != 2 </p>
 * <p>The condition is represented as SELECTOR != RIGHT_OPERAND</p>
 */
public class NotEqualsCondition extends Condition {
    /**
     * Used to select the left operand
     */
    private JsonSelector selector;

    /**
     * Right operand
     */
    private Object rightOperand;

    public NotEqualsCondition() {
        // For marshalling / unmarshalling
    }

    public NotEqualsCondition(JsonSelector selector, Object rightOperand) {
        this.selector = selector;
        this.rightOperand = rightOperand;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        JsonNode input = context.getInput();
        if (input == null || input.isNull()) {
            return false;
        }

        Object leftOperand = selector.apply(input);
        if (Objects.requireNonNull(leftOperand) instanceof String string) {
            return !string.equals(rightOperand);
        }
        if (Objects.requireNonNull(leftOperand) instanceof Number number) {
            return number.doubleValue() != (double) rightOperand;
        }
        if (Objects.requireNonNull(leftOperand) instanceof Boolean bool) {
            return bool != rightOperand;
        }
        return false;
    }

    public JsonSelector getSelector() {
        return selector;
    }

    public void setSelector(JsonSelector selector) {
        this.selector = selector;
    }

    public Object getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(Object rightOperand) {
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
        return "NotEqualsCondition{" +
                "selector=" + selector +
                ", rightOperand=" + rightOperand +
                '}';
    }
}
