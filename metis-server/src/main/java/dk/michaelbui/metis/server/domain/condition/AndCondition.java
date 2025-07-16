package dk.michaelbui.metis.server.domain.condition;

public class AndCondition extends Condition {
    private final Condition left;
    private final Condition right;

    public AndCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate() {
        return left.evaluate() && right.evaluate();
    }

    @Override
    public void setContext(EvaluationContext context) {
        this.left.setContext(context);
        this.right.setContext(context);
    }

    @Override
    public String toString() {
        return "AndCondition{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
