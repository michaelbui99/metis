package dk.michaelbui.metis.server.domain.condition;

public abstract class Condition {
    private EvaluationContext context;


    protected Condition() {
    }

    protected Condition(EvaluationContext context) {
        this.context = context;
    }

    public abstract boolean evaluate();

    public boolean evaluate(EvaluationContext context) {
        setContext(context);
        return this.evaluate();
    }

    public EvaluationContext getContext() {
        return context;
    }

    public void setContext(EvaluationContext context) {
        this.context = context;
    }
}
