package dk.michaelbui.metis.server.domain.rule;

import java.util.List;

public interface RulesService {
    void addRule(Rule rule);
    List<Rule> getRules();
}
