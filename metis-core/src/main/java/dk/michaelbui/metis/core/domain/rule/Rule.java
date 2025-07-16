package dk.michaelbui.metis.core.domain.rule;

import dk.michaelbui.metis.core.domain.condition.Condition;
import dk.michaelbui.metis.core.domain.event.EventTemplate;

public class Rule {
    /**
     * Name of the rule. The name uniquely identifies a Rule in the system.
     * */
    private RuleName name;

    private Condition condition;

    private EventTemplate eventTemplate;

    private RuleContext context;


    public RuleName getName() {
        return name;
    }

    public EventTemplate getEventTemplate() {
        return eventTemplate;
    }

    public void setEventTemplate(EventTemplate eventTemplate) {
        this.eventTemplate = eventTemplate;
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
                ", event=" + eventTemplate +
                ", context=" + context +
                '}';
    }
}