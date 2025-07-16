package dk.michaelbui.metis.server.domain.condition;

public class OrCondition extends Condition {
    private Condition left;
    private Condition right;

    public OrCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate() {
        return left.evaluate() || right.evaluate();
    }

    @Override
    public void setContext(EvaluationContext context) {
        this.left.setContext(context);
        this.right.setContext(context);
    }

    @Override
    public String toString() {
        return "OrCondition{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
