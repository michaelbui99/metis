package dk.michaelbui.metis.server.domain;

public class RuleName {
    private static final String VALID_RULE_NAME_PATTERN = "^[a-zA-Z_]\\w*$";
    private String name;

    public RuleName() {
    }

    public RuleName(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    public boolean isValidRuleName() {
        return name.matches(VALID_RULE_NAME_PATTERN);
    }

    @Override
    public String toString() {
        return "RuleName{" +
                "name='" + name + '\'' +
                '}';
    }
}
