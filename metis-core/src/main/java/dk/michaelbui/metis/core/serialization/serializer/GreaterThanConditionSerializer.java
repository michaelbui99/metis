package dk.michaelbui.metis.core.serialization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dk.michaelbui.metis.core.domain.condition.GreaterThanCondition;
import dk.michaelbui.metis.core.serialization.ConditionType;
import dk.michaelbui.metis.core.serialization.Constants;

import java.io.IOException;

public class GreaterThanConditionSerializer extends StdSerializer<GreaterThanCondition> {

    public GreaterThanConditionSerializer() {
        this(null);
    }

    public GreaterThanConditionSerializer(Class<GreaterThanCondition> t) {
        super(t);
    }


    @Override
    public void serialize(GreaterThanCondition value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(Constants.METIS_CONDITION_TYPE, ConditionType.GREATER_THAN.getValue());
        gen.writeObjectField(Constants.SELECTOR_FIELD, value.getSelector());
        gen.writeNumberField(Constants.RIGHT_OPERAND_FIELD, value.getRightOperand().doubleValue());
        gen.writeBooleanField(Constants.ALLOW_EQUALS_FIELD, value.isAllowEquals());
        gen.writeEndObject();
    }
}
