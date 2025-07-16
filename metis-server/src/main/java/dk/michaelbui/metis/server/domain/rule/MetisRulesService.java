package dk.michaelbui.metis.server.domain.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetisRulesService implements RulesService{
    private RulesRepository rulesRepository;

    @Autowired
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
