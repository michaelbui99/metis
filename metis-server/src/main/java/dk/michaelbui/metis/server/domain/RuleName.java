package dk.michaelbui.metis.server.domain;

public class RuleName {
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
        throw new RuntimeException("Not implemented yet");
    }
}
