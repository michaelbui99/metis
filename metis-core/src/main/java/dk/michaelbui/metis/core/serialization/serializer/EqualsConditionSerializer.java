package dk.michaelbui.metis.core.serialization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dk.michaelbui.metis.core.domain.condition.EqualsCondition;
import dk.michaelbui.metis.core.serialization.ConditionType;
import dk.michaelbui.metis.core.serialization.Constants;

import java.io.IOException;

public class EqualsConditionSerializer extends StdSerializer<EqualsCondition> {

    public EqualsConditionSerializer() {
        this(null);
    }

    public EqualsConditionSerializer(Class<EqualsCondition> t) {
        super(t);
    }

    @Override
    public void serialize(EqualsCondition value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(Constants.METIS_CONDITION_TYPE, ConditionType.EQUALS.getValue());
        gen.writeObjectField(Constants.SELECTOR_FIELD, value.getSelector());

        switch (value.getRightOperand()) {
            case String s -> gen.writeStringField(Constants.RIGHT_OPERAND_FIELD, s);
            case Boolean bool -> gen.writeBooleanField(Constants.RIGHT_OPERAND_FIELD, bool);
            case Number n -> gen.writeNumberField(Constants.RIGHT_OPERAND_FIELD, n.doubleValue());
            case Object o -> gen.writeObjectField(Constants.RIGHT_OPERAND_FIELD, o);
        }

        gen.writeEndObject();
    }
}
