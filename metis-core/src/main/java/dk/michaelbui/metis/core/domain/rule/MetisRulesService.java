package dk.michaelbui.metis.core.domain.rule;

import java.util.List;

public class MetisRulesService implements RulesService{
    private RulesRepository rulesRepository;

    public MetisRulesService(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    @Override
    public void addRule(Rule rule) {
        // TODO: add validation logic
        this.rulesRepository.addRule(rule);
    }

    public List<Rule> getRules(){
        return rulesRepository.getRules();
    }
}
