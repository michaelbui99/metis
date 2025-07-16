package dk.michaelbui.metis.server.domain.condition;

public abstract class Condition {

    protected Condition() {
    }

    public abstract boolean evaluate(EvaluationContext context);
}
