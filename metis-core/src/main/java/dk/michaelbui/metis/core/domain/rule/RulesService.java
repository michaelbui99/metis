package dk.michaelbui.metis.core.domain.rule;

import java.util.List;

public interface RulesService {
    void addRule(Rule rule);
    List<Rule> getRules();
}
