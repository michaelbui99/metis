package dk.michaelbui.metis.core.domain.condition;

public class AndCondition extends Condition {
    private Condition left;
    private Condition right;

    public AndCondition() {
        // For marshalling / unmarshalling
    }

    public AndCondition(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(EvaluationContext context) {
        return left.evaluate(context) && right.evaluate(context);
    }

    public Condition getLeft() {
        return left;
    }

    public void setLeft(Condition left) {
        this.left = left;
    }

    public Condition getRight() {
        return right;
    }

    public void setRight(Condition right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AndCondition{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
