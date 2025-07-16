package dk.michaelbui.metis.server.domain.condition;

public class OrCondition extends Condition {
    private Condition left;
    private Condition right;

    public OrCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        return left.evaluate(context) || right.evaluate(context);
    }

    @Override
    public String toString() {
        return "OrCondition{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
