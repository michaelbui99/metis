package dk.michaelbui.metis.server.domain.rule;

import java.util.List;
import java.util.Optional;

public interface RulesRepository {
    Optional<Rule> getRule(String name);
    List<Rule> getRules();
    void addRule(Rule rule);
    void updateRule(String name, Rule rule);
}
