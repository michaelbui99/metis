package dk.michaelbui.metis.server.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Engine {
    private RuleRepository ruleRepository;

    @Autowired
    public Engine(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
}
