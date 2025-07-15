package dk.michaelbui.metis.server.dsl;

import dk.michaelbui.metis.dsl.*;
import dk.michaelbui.metis.server.domain.JsonSelector;
import dk.michaelbui.metis.server.domain.Rule;
import dk.michaelbui.metis.server.domain.RuleName;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Responsible for converting Metis DSL to a {@link Rule}
 * */
public class MetisRuleVisitor extends MetisDslBaseVisitor<Rule> {
    private Logger LOGGER = LoggerFactory.getLogger(MetisRuleVisitor.class);
    private Rule rule;

    public MetisRuleVisitor() {
    }

    @Override
    public Rule visitMetisRule(MetisDslParser.MetisRuleContext ctx) {
        rule = new Rule();
        visitNamedRule(ctx.namedRule());
        return rule;
    }

    @Override
    public Rule visitNamedRule(MetisDslParser.NamedRuleContext ctx) {
        TerminalNode identifier = ctx.IDENTIFIER();
        if (identifier == null) {
            throw new ParseException("Failed to parse DSL: Missing identifier");
        }

        RuleName ruleName = new RuleName(identifier.getText());
        rule.setName(ruleName);

        if (ctx.withClause() != null){
            visitWithClause(ctx.withClause());
        }
        visitWhenClause(ctx.whenClause());

        return super.visitNamedRule(ctx);
    }

    @Override
    public Rule visitWithClause(MetisDslParser.WithClauseContext ctx) {
        for (MetisDslParser.BindingContext binding : ctx.bindings().binding()){
            if (binding.IDENTIFIER() == null){
                throw new ParseException("Failed to parse DSL: Missing identifier");
            }

            if (binding.jsonSelector() == null){
                throw new ParseException("Failed to parse DSL: Missing json selector");
            }

            String identifier = binding.IDENTIFIER().getText();
            String selector = binding.jsonSelector().getText();
            rule.getContext().putBinding(identifier, new JsonSelector(selector));
        }
        return super.visitWithClause(ctx);
    }

    @Override
    public Rule visitWhenClause(MetisDslParser.WhenClauseContext ctx) {
        visit(ctx.condition());
        return super.visitWhenClause(ctx);
    }

    @Override
    public Rule visitOrExpr(MetisDslParser.OrExprContext ctx) {
        LOGGER.info("Visiting OR expression");
        return super.visitOrExpr(ctx);
    }

    @Override
    public Rule visitAndExpr(MetisDslParser.AndExprContext ctx) {
        LOGGER.info("Visiting AND expression");
        return super.visitAndExpr(ctx);
    }

    @Override
    public Rule visitNotExpr(MetisDslParser.NotExprContext ctx) {
        LOGGER.info("Visiting NOT expression");
        return super.visitNotExpr(ctx);
    }
}
