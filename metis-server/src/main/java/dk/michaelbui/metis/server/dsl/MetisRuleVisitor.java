package dk.michaelbui.metis.server.dsl;

import dk.michaelbui.metis.dsl.*;
import dk.michaelbui.metis.server.domain.selector.JsonSelector;
import dk.michaelbui.metis.server.domain.Rule;
import dk.michaelbui.metis.server.domain.RuleContext;
import dk.michaelbui.metis.server.domain.RuleName;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Responsible for converting Metis DSL to a {@link Rule}
 * */
public class MetisRuleVisitor extends MetisDslBaseVisitor<Rule> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetisRuleVisitor.class);
    private Rule rule;

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
        return null;
    }

    @Override
    public Rule visitWithClause(MetisDslParser.WithClauseContext ctx) {
        rule.setContext(new RuleContext());
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
        return null;
    }

    @Override
    public Rule visitWhenClause(MetisDslParser.WhenClauseContext ctx) {
        visitCondition(ctx.condition());
        visitAction(ctx.action());
        return null;
    }

    @Override
    public Rule visitCondition(MetisDslParser.ConditionContext ctx) {
        visitOrExpr(ctx.orExpr());
        return null;
    }

    @Override
    public Rule visitOrExpr(MetisDslParser.OrExprContext ctx) {
        LOGGER.info("Visiting OR expression");
        return null;
    }

    @Override
    public Rule visitAndExpr(MetisDslParser.AndExprContext ctx) {
        LOGGER.info("Visiting AND expression");
        return null;
    }

    @Override
    public Rule visitNotExpr(MetisDslParser.NotExprContext ctx) {
        LOGGER.info("Visiting NOT expression");
        return null;
    }

    @Override
    public Rule visitAction(MetisDslParser.ActionContext ctx) {
        LOGGER.info("Visiting action");
        return null;
    }
}
