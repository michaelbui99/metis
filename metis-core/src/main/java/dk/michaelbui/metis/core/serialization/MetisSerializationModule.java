package dk.michaelbui.metis.core.serialization;

import com.fasterxml.jackson.databind.module.SimpleModule;
import dk.michaelbui.metis.core.domain.condition.*;
import dk.michaelbui.metis.core.serialization.serializer.*;

public class MetisSerializationModule {
    private final SimpleModule module;

    public MetisSerializationModule() {
        this.module = new SimpleModule();
        module.addSerializer(AndCondition.class, new AndConditionSerializer());
        module.addSerializer(EqualsCondition.class, new EqualsConditionSerializer());
        module.addSerializer(GreaterThanCondition.class, new GreaterThanConditionSerializer());
        module.addSerializer(LessThanCondition.class, new LessThanConditionSerializer());
        module.addSerializer(NotCondition.class, new NotConditionSerializer());
        module.addSerializer(NotEqualsCondition.class, new NotEqualsConditionSerializer());
        module.addSerializer(OrCondition.class, new OrConditionSerializer());
    }

    public SimpleModule getModule() {
        return module;
    }
}
