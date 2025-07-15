package dk.michaelbui.metis.server.domain;

import dk.michaelbui.metis.server.domain.condition.Condition;
import dk.michaelbui.metis.server.domain.event.EventTemplate;

import java.util.List;

public class Rule {
    /**
     * Name of the rule. The name uniquely identifies a Rule in the system.
     * */
    private RuleName name;

    /**
     * Conditions that must be evaluated to true before {@code event} is raised
     * */
    private List<Condition> conditions;

    private EventTemplate event;

    private RuleContext context;


    public RuleName getName() {
        return name;
    }

    public List<Condition> getConditions() {
        return conditions;
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

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public RuleContext getContext() {
        return context;
    }

    public void setContext(RuleContext context) {
        this.context = context;
    }
}
