package dk.michaelbui.metis.core.domain.condition;

public abstract class Condition {

    protected Condition() {
    }

    public abstract boolean evaluate(EvaluationContext context);
}
