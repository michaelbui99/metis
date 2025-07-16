package dk.michaelbui.metis.core.domain.condition;

public class AndCondition extends Condition {
    private final Condition left;
    private final Condition right;

    public AndCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        return left.evaluate(context) && right.evaluate(context);
    }

    @Override
    public String toString() {
        return "AndCondition{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
