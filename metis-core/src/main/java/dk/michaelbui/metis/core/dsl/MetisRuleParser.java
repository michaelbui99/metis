package dk.michaelbui.metis.core.dsl;

import dk.michaelbui.metis.dsl.MetisDslLexer;
import dk.michaelbui.metis.dsl.MetisDslParser;
import dk.michaelbui.metis.core.domain.rule.Rule;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

public class MetisRuleParser {
    private final MetisRuleVisitor visitor;

    public MetisRuleParser() {
        this.visitor = new MetisRuleVisitor();
    }

    public Rule parse(String dsl) {
        ANTLRInputStream inputStream = new ANTLRInputStream(dsl);
        MetisDslLexer lexer = new MetisDslLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MetisDslParser parser = new MetisDslParser(tokens);
        try {
            ParseTree tree = parser.metisRule();
            return (Rule) visitor.visit(tree);
        } catch (RecognitionException e) {
            throw new ParseException("Failed to parse DSL: " + e.getMessage(), e);
        }
    }
}
