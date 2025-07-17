package dk.michaelbui.metis.core.serialization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dk.michaelbui.metis.core.domain.condition.OrCondition;
import dk.michaelbui.metis.core.serialization.ConditionType;
import dk.michaelbui.metis.core.serialization.Constants;

import java.io.IOException;

public class OrConditionSerializer extends StdSerializer<OrCondition> {

    public OrConditionSerializer() {
        this(null);
    }

    public OrConditionSerializer(Class<OrCondition> t) {
        super(t);
    }

    @Override
    public void serialize(OrCondition value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(Constants.METIS_CONDITION_TYPE, ConditionType.OR.getValue());
        gen.writeObjectField(Constants.LEFT_FILED, value.getLeft());
        gen.writeObjectField(Constants.RIGHT_FIELD, value.getRight());
        gen.writeEndObject();
    }
}
