package dk.michaelbui.metis.server.domain.condition;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.server.domain.selector.JsonSelector;

import java.util.Objects;

/**
 * <p>Represents a equals condition such as "TEST" == "TEST" or 2 == 2 or true == true </p>
 * <p>The condition is represented as SELECTOR == RIGHT_OPERAND</p>
 */
public class EqualsCondition extends Condition {
    /**
     * Used to select the left operand
     */
    private final JsonSelector selector;

    /**
     * Right operand
     */
    private final Object rightOperand;

    public EqualsCondition(JsonSelector selector, Object rightOperand) {
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
            return string.equals(rightOperand);
        }
        if (Objects.requireNonNull(leftOperand) instanceof Number number) {
            return number.doubleValue() == (double)rightOperand;
        }
        if (Objects.requireNonNull(leftOperand) instanceof Boolean bool) {
            return bool == rightOperand;
        }
        return false;
    }

    @Override
    public String toString() {
        return "EqualsCondition{" +
                "selector=" + selector +
                ", rightOperand=" + rightOperand +
                '}';
    }
}
