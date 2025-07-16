package dk.michaelbui.metis.server.domain.dsl;

import dk.michaelbui.metis.dsl.*;
import dk.michaelbui.metis.server.domain.Assignment;
import dk.michaelbui.metis.server.domain.condition.*;
import dk.michaelbui.metis.server.domain.event.EventTemplate;
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
public class MetisRuleVisitor extends MetisDslBaseVisitor<Object> {
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

        if (ctx.withClause() != null) {
            visit(ctx.withClause());
        }
        visit(ctx.whenClause());
        return null;
    }

    // NOTE: Skip support for bindings for now to get MVP up and running.
    @Override
    public Rule visitWithClause(MetisDslParser.WithClauseContext ctx) {
        rule.setContext(new RuleContext());
        for (MetisDslParser.BindingContext binding : ctx.bindings().binding()) {
            if (binding.IDENTIFIER() == null) {
                throw new ParseException("Failed to parse DSL: Missing identifier");
            }

            if (binding.jsonSelector() == null) {
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
        Condition condition = visitCondition(ctx.condition());
        rule.setCondition(condition);

        visit(ctx.action());
        return null;
    }

    @Override
    public Condition visitCondition(MetisDslParser.ConditionContext ctx) {
        return (Condition)visit(ctx.orExpr());
    }

    @Override
    public Object visitOrExpr(MetisDslParser.OrExprContext ctx) {
        LOGGER.info("Visiting OR expression");

        if (ctx.andExpr().size() == 1) {
            return visit(ctx.andExpr(0));
        }

        Condition left = (Condition) visit(ctx.andExpr(0));
        for (int i = 1; i < ctx.andExpr().size(); i++) {
            Condition right = (Condition) visit(ctx.andExpr(i));
            left = new OrCondition(left, right);
        }
        return left;
    }

    @Override
    public Object visitAndExpr(MetisDslParser.AndExprContext ctx) {
        if (ctx.notExpr().size() == 1) {
            return visit(ctx.notExpr(0));
        }

        Condition left = (Condition) visit(ctx.notExpr(0));
        for (int i = 1; i < ctx.notExpr().size(); i++) {
            Condition right = (Condition) visit(ctx.notExpr(i));
            left = new AndCondition(left, right);
        }
        return left;
    }

    @Override
    public Object visitNotExpr(MetisDslParser.NotExprContext ctx) {
        LOGGER.info("Visiting NOT expression");
        if (ctx.NOT() != null) {
            Object inner = visit(ctx.notExpr());
            return new NotCondition(inner);
        }
        return visit(ctx.primaryCondition());
    }

    @Override
    public Object visitParenthesizedCondition(MetisDslParser.ParenthesizedConditionContext ctx) {
        return visit(ctx.condition());
    }

    @Override
    public Object visitBinaryExpression(MetisDslParser.BinaryExpressionContext ctx) {
        String operator = ctx.comparisonOp().getText();
        // TODO: Error handling in case types are not matching
        return switch (operator) {
            case ">" -> {
                JsonSelector selector = (JsonSelector) visit(ctx.expression().get(0));
                Object operand = visit(ctx.expression().get(1));
                yield new GreaterThanCondition(selector, (Number) operand, false);
            }
            case ">=" -> {
                JsonSelector selector = (JsonSelector) visit(ctx.expression().get(0));
                Object operand = visit(ctx.expression().get(1));
                yield new GreaterThanCondition(selector, (Number) operand, true);
            }
            case "<" -> {
                JsonSelector selector = (JsonSelector) visit(ctx.expression().get(0));
                Object operand = visit(ctx.expression().get(1));
                yield new LessThanCondition(selector, (Number) operand, false);
            }
            case "<=" -> {
                JsonSelector selector = (JsonSelector) visit(ctx.expression().get(0));
                Object operand = visit(ctx.expression().get(1));
                yield new LessThanCondition(selector, (Number) operand, true);
            }
            case "==" -> {
                JsonSelector selector = (JsonSelector) visit(ctx.expression().get(0));
                Object operand = visit(ctx.expression().get(1));
                yield new EqualsCondition(selector, operand);
            }
            case "=" -> {
                String left = (String) visit(ctx.expression().get(0));
                String right = (String) visit(ctx.expression().get(1));
                yield new Assignment(left, right);
            }
            default -> throw new ParseException("Failed to parse binary expression");
        };
    }

    @Override
    public Object visitJsonSelector(MetisDslParser.JsonSelectorContext ctx) {
        StringBuilder selector = new StringBuilder("$");
        if (ctx.jsonPath() != null) {
            selector.append(ctx.jsonPath().getText());
        }
        return new JsonSelector(selector.toString());
    }

    @Override
    public Object visitLiteral(MetisDslParser.LiteralContext ctx) {
        if (ctx.BOOLEAN_LITERAL() != null) {
            return Boolean.parseBoolean(ctx.BOOLEAN_LITERAL().getText());
        }

        if (ctx.STRING_LITERAL() != null) {
            return ctx.STRING_LITERAL().getText();
        }

        if (ctx.NUMBER_LITERAL() != null) {
            return Double.parseDouble(ctx.NUMBER_LITERAL().getText());
        }

        return null;
    }

    @Override
    public EventTemplate visitAction(MetisDslParser.ActionContext ctx) {
        LOGGER.info("Visiting action");
        return null;
    }
}
