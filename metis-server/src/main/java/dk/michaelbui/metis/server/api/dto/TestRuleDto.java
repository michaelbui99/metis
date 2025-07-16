package dk.michaelbui.metis.server.api.dto;

public class TestRuleDto {
    private String rule;

    public TestRuleDto() {
    }

    public TestRuleDto(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
