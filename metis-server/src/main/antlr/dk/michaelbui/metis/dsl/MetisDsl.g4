grammar MetisDsl;

@header {
package dk.michaelbui.metis.dsl;
}

// =======================
// Parser Rules
// =======================

metisRule
    : namedRule
    ;

namedRule
    : RULE IDENTIFIER withClause? whenClause
    ;

withClause
    : WITH '(' bindings ')'
    ;

bindings
    : binding (',' binding)*
    ;

binding
    : IDENTIFIER '=' jsonSelector
    ;

whenClause
    : WHEN '(' condition ')' THEN action
    ;

condition
    : orExpr
    ;

orExpr
    : andExpr (OR andExpr)*
    ;

andExpr
    : notExpr (AND notExpr)*
    ;

notExpr
    : NOT notExpr
    | primaryCondition
    ;

primaryCondition
    : '(' condition ')'     # ParenthesizedCondition
    | binaryExpression      # BinaryComparison
    | expression            # ExpressionCondition
    ;

binaryExpression
    : expression comparisonOp expression
    ;

comparisonOp
    : '>'
    | '<'
    | '>='
    | '<='
    | '=='
    | '!='
    ;

// =======================
// Action
// =======================

action
    : RAISE event
    ;

event
    : IDENTIFIER '(' params? ')'
    ;

params
    : param (',' param)*
    ;

param
    : IDENTIFIER '=' expression
    ;

// =======================
// Expressions
// =======================

expression
    : literal
    | jsonSelector
    | '$' IDENTIFIER
    | interpolatedString
    ;

literal
    : NUMBER_LITERAL
    | STRING_LITERAL
    | BOOLEAN_LITERAL
    ;

jsonSelector
    : DOLLAR jsonPath
    ;

jsonPath
    : ('.' IDENTIFIER | '[' NUMBER_LITERAL ']')+
    ;

interpolatedString
    : STRING_LITERAL
    ;

// =======================
// Lexer Rules
// =======================

RULE      : 'RULE';
WHEN      : 'WHEN';
THEN      : 'THEN';
WITH      : 'WITH';
RAISE     : 'RAISE';

AND       : 'AND';
OR        : 'OR';
NOT       : '!';

BOOLEAN_LITERAL
    : 'true' | 'false'
    ;

NUMBER_LITERAL
    : [0-9]+ ('.' [0-9]+)?
    ;

STRING_LITERAL
    : '"' ( ~["\\] | '\\' . | '$' IDENTIFIER )* '"'
    ;

IDENTIFIER
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

// Symbols
LPAREN    : '(';
RPAREN    : ')';
COMMA     : ',';
DOT       : '.';
EQ        : '=';
DOLLAR    : '$';

// Whitespace and Comments
WS        : [ \t\r\n]+ -> skip;
