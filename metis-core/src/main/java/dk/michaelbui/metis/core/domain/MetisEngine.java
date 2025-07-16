package dk.michaelbui.metis.core.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.michaelbui.metis.core.domain.condition.EvaluationContext;
import dk.michaelbui.metis.core.domain.event.Event;
import dk.michaelbui.metis.core.domain.event.EventsService;
import dk.michaelbui.metis.core.domain.rule.Rule;
import dk.michaelbui.metis.core.domain.rule.RulesService;

import java.util.List;

public class MetisEngine implements Engine {
    private final RulesService rulesService;
    private final EventsService eventsService;

    public MetisEngine(RulesService rulesService, EventsService eventsService) {
        this.rulesService = rulesService;
        this.eventsService = eventsService;
    }

    @Override
    public List<Event> input(String jsonString) {
        JsonNode json;
        try {
            json = new ObjectMapper().readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new DomainException("Failed to parse JSON", e);
        }

        List<Rule> rules = this.rulesService.getRules();
        EvaluationContext evaluationContext = new EvaluationContext(json);

        return rules.stream()
                .filter(rule -> rule.getCondition().evaluate(evaluationContext))
                .map(Rule::getEventTemplate)
                .map(template -> eventsService.eventFromTemplate(template, json))
                .toList();
    }
}
