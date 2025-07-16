package dk.michaelbui.metis.server.api;

import dk.michaelbui.metis.server.api.dto.ReadRuleDto;
import dk.michaelbui.metis.core.dsl.MetisRuleParser;
import dk.michaelbui.metis.core.domain.rule.Rule;
import dk.michaelbui.metis.core.domain.rule.RulesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rules")
public class RulesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RulesController.class);
    private final RulesService rulesService;
    private final MetisRuleParser metisRuleParser;

    @Autowired
    public RulesController(RulesService rulesService) {
        this.rulesService = rulesService;
        metisRuleParser = new MetisRuleParser();
    }

    @PostMapping
    public ResponseEntity<Void> addRule(@RequestBody  String dsl){
        Rule rule = metisRuleParser.parse(dsl);
        rulesService.addRule(rule);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ReadRuleDto>> getRules(){
        List<ReadRuleDto> rules = rulesService.getRules().stream()
                .map(rule -> new ReadRuleDto(rule.getName().value(), rule.getCondition().toString()))
                .toList();
        return ResponseEntity.ok(rules);
    }
}

