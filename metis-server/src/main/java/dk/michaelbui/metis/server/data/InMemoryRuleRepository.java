package dk.michaelbui.metis.server.data;

import dk.michaelbui.metis.core.domain.rule.Rule;
import dk.michaelbui.metis.core.domain.rule.RulesRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRuleRepository implements RulesRepository {
    private Map<String, Rule> rules;

    public InMemoryRuleRepository() {
        rules = new HashMap<>();
    }

    @Override
    public Optional<Rule> getRule(String name) {
        return Optional.ofNullable(rules.get(name));
    }

    @Override
    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public void addRule(Rule rule) {
        rules.put(rule.getName().value(), rule);
    }

    @Override
    public void updateRule(String name, Rule rule) {
        rules.put(rule.getName().value(), rule);
    }
}
