package dk.michaelbui.metis.server.api.dto;

public class ReadRuleDto {
    private String name;
    // TODO: update when better readable representation is implemented
    private String condition;

    public ReadRuleDto(String name, String condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
