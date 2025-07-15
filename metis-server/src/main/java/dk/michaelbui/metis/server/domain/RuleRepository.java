package dk.michaelbui.metis.server.domain;

import java.util.List;
import java.util.Optional;

public interface RuleRepository {
    Optional<Rule> getRule(String name);
    List<Rule> getRules();
    void addRule(Rule rule);
    void updateRule(String name, Rule rule);
}
