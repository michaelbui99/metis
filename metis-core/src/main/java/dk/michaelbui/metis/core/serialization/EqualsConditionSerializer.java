package dk.michaelbui.metis.core.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dk.michaelbui.metis.core.domain.condition.EqualsCondition;

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
        gen.writeStringField(Constants.METIS_CONDITION_TYPE, "equals");
    }
}
