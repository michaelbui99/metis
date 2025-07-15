package dk.michaelbui.metis.server.api;

import dk.michaelbui.metis.dsl.MetisDslLexer;
import dk.michaelbui.metis.dsl.MetisDslParser;
import dk.michaelbui.metis.server.domain.Rule;
import dk.michaelbui.metis.server.api.dto.ReadRuleDto;
import dk.michaelbui.metis.server.dsl.MetisRuleVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ReadRuleDto test(@RequestBody String dsl) {
        ANTLRInputStream inputStream = new ANTLRInputStream(dsl);
        MetisDslLexer lexer = new MetisDslLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MetisDslParser parser = new MetisDslParser(tokens);
        ParseTree tree = parser.metisRule();

        MetisRuleVisitor visitor = new MetisRuleVisitor();
        Rule rule = visitor.visit(tree);
        return new ReadRuleDto(UUID.randomUUID().toString(), rule.getName().value());
    }
}
