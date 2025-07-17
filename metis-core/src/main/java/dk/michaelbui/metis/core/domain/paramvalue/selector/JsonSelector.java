package dk.michaelbui.metis.core.domain.paramvalue.selector;

import com.fasterxml.jackson.databind.JsonNode;
import dk.michaelbui.metis.core.domain.event.ParamValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JsonSelector implements ParamValue {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSelector.class);
    private static final String VALID_SELECTOR_PATTERN = "^\\$(\\.[a-zA-Z_]\\w*|\\[\\d+\\])*$";
    private static final String ROOT_SYMBOL = "$";
    private static final String ARRAY_INDEX_STRING_PATTERN = "^[a-zA-Z_]\\w*\\[\\d+\\]$";

    private String selector;

    public JsonSelector() {
    }

    public JsonSelector(String selector) {
        if (!selector.matches(VALID_SELECTOR_PATTERN)) {
            throw new IllegalArgumentException("Invalid selector: " + selector);
        }
        this.selector = selector;
    }

    public Object apply(JsonNode node) {
        String[] parts = selector.split("\\.");

        // Select root node
        if (parts.length == 1 && parts[0].equals(ROOT_SYMBOL)) {
            return node;
        }

        JsonNode current = node;
        for (String fieldName : parts) {
            if (fieldName.equals(ROOT_SYMBOL)) {
                current = node;
                continue;
            }

            if (fieldName.matches(ARRAY_INDEX_STRING_PATTERN)) {
                current = selectArrayElement(current, fieldName);
            }else{
                current = current.get(fieldName);
            }

            if (current == null) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Field '{}' not found when processing selector '{}' for node '{}'", fieldName, selector, node.toPrettyString());
                }
                return null;
            }
            if (current.isNull()) {
                return null;
            }
        }

        if (current.isNumber()) {
            return current.numberValue();
        } else if (current.isTextual()) {
            return current.textValue();
        } else if (current.isBoolean()) {
            return current.booleanValue();
        } else if (current.isArray() || current.isObject()) {
            return current;
        }
        throw new IllegalStateException("Failed to select an expected value when processing selector " + "'" + selector + "'");
    }

    private JsonNode selectArrayElement(JsonNode node, String fieldName) {
        String property = fieldName.split("\\[")[0];
        JsonNode arrayProperty = node.get(property);
        if (!arrayProperty.isArray()) {
            throw new SelectorException(String.format("Expected array property when applying sub-selector '%s', but received '%s'", fieldName, arrayProperty.toPrettyString()));
        }
        Optional<Integer> index = readIndex(fieldName);
        if (index.isPresent()) {
            return arrayProperty.get(index.get());
        }else{
            throw new SelectorException(String.format("Could not find index for property '%s'", fieldName));
        }
    }

    private Optional<Integer> readIndex(String arrayIndexString) {
        char[] chars = arrayIndexString.toCharArray();
        if (arrayIndexString.contains("[")){
            // "someText[0]" --> ["someText", "10]"]
            String[] parts  = arrayIndexString.split("\\[");
            if (parts.length != 2){
                LOGGER.debug("Invalid array index string '{}'", arrayIndexString);
                return Optional.empty();
            }

            chars = parts[1].toCharArray();
        }

        StringBuilder index = new StringBuilder();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                index.append(c);
            }
        }

        if (index.isEmpty()) {
            LOGGER.debug("Failed to read index from '{}'", arrayIndexString);
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(index.toString()));
        } catch (NumberFormatException e) {
            LOGGER.debug("Failed to parse '{}' to integer", index);
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "JsonSelector{" +
                "selector='" + selector + '\'' +
                '}';
    }
}
