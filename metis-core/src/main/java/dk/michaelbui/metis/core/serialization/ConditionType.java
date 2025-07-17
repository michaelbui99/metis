package dk.michaelbui.metis.core.serialization;

public enum ConditionType {
    AND("and"),
    EQUALS("equals"),
    GREATER_THAN("greater_than"),
    LESS_THAN("less_than"),
    NOT("not"),
    NOT_EQUALS("not_equals"),
    OR("or");


    private String value;

    ConditionType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
