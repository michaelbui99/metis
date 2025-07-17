package dk.michaelbui.metis.core.domain.paramvalue;

import dk.michaelbui.metis.core.domain.event.ParamValue;
import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;

public class ParamValueUtil {
    private ParamValueUtil() {}

    public static ParamValue of(Object value) {
        return switch (value) {
            case JsonSelector selector -> selector;
            case BindingValue bv -> bv;
            default -> new LiteralValue(value);
        };
    }
}
