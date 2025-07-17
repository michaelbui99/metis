package dk.michaelbui.metis.core.serialization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dk.michaelbui.metis.core.domain.condition.NotCondition;
import dk.michaelbui.metis.core.serialization.ConditionType;
import dk.michaelbui.metis.core.serialization.Constants;

import java.io.IOException;

public class NotConditionSerializer extends StdSerializer<NotCondition> {
    public NotConditionSerializer() {
        this(null);
    }

    public NotConditionSerializer(Class<NotCondition> t) {
        super(t);
    }

    @Override
    public void serialize(NotCondition value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(Constants.METIS_CONDITION_TYPE, ConditionType.NOT.getValue());

        switch (value.getInner()) {
            case Boolean b -> gen.writeBooleanField(Constants.INNER_FIELD, b);
            case Object o -> gen.writeObjectField(Constants.INNER_FIELD, o);
        }

        gen.writeEndObject();
    }
}
