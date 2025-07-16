package dk.michaelbui.metis.server.domain;

import dk.michaelbui.metis.server.domain.condition.Condition;
import dk.michaelbui.metis.server.domain.event.EventTemplate;

public class Rule {
    /**
     * Name of the rule. The name uniquely identifies a Rule in the system.
     * */
    private RuleName name;

    private Condition condition;

    private EventTemplate event;

    private RuleContext context;


    public RuleName getName() {
        return name;
    }

    public EventTemplate getEvent() {
        return event;
    }

    public void setEvent(EventTemplate event) {
        this.event = event;
    }

    public void setName(RuleName name) {
        this.name = name;
    }

    public RuleContext getContext() {
        return context;
    }

    public void setContext(RuleContext context) {
        this.context = context;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name=" + name +
                ", condition=" + condition +
                ", event=" + event +
                ", context=" + context +
                '}';
    }
}