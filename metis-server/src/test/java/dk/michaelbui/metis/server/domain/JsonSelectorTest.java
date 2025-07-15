package dk.michaelbui.metis.server.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.michaelbui.metis.server.domain.selector.JsonSelector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class JsonSelectorTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void invalidSelectorWhenNotStartingWithRootSymbol(){
        // Arrange
        String invalidSelector = ".products";

        // Act
        Executable createSelector =  () -> new JsonSelector(invalidSelector);

        // Assert
        assertThrows(IllegalArgumentException.class, createSelector);
    }


    @Test
    void validSelector(){
        // Arrange
        String validSelector = "$.products";

        // Act
        Executable createSelector =  () -> new JsonSelector(validSelector);

        // Assert
        assertDoesNotThrow(createSelector);
    }

    @Test
    void rootSymbolOnlyIsValidSelector(){
        // Arrange
        String validSelector = "$";

        // Act
        Executable createSelector =  () -> new JsonSelector(validSelector);

        // Assert
        assertDoesNotThrow(createSelector);
    }

    @Test
    void selectString() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                   "name": "Bob" ,
                   "age": 18,
                   "hasDriverLicense": true
                }
                """;
        JsonNode json = objectMapper.readTree(jsonString);
        JsonSelector selector = new JsonSelector("$.name");

        // Act
        Object value = selector.apply(json);

        // Assert
        assertInstanceOf(String.class, value);
        assertEquals("Bob", value);
    }

    @Test
    void selectNumber() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                   "name": "Bob" ,
                   "age": 18,
                   "hasDriverLicense": true
                }
                """;
        JsonNode json = objectMapper.readTree(jsonString);
        JsonSelector selector = new JsonSelector("$.age");

        // Act
        Object value = selector.apply(json);

        // Assert
        assertInstanceOf(Number.class, value);
        assertEquals(18.0, ((Number)value).doubleValue());
    }

    @Test
    void selectBoolean() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                   "name": "Bob" ,
                   "age": 18,
                   "hasDriverLicense": true
                }
                """;
        JsonNode json = objectMapper.readTree(jsonString);
        JsonSelector selector = new JsonSelector("$.hasDriverLicense");

        // Act
        Object value = selector.apply(json);

        // Assert
        assertInstanceOf(Boolean.class, value);
        assertTrue((boolean)value);
    }

    @Test
    void selectFromArray() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                   "name": "Bob" ,
                   "age": 18,
                   "hasDriverLicense": true,
                   "degrees": ["Bachelor of Science", "Bachelor of Arts", "High School"]
                }
                """;
        JsonNode json = objectMapper.readTree(jsonString);
        JsonSelector selector0 = new JsonSelector("$.degrees[0]");
        JsonSelector selector1 = new JsonSelector("$.degrees[1]");
        JsonSelector selector2 = new JsonSelector("$.degrees[2]");

        // Act
        Object value0 = selector0.apply(json);
        Object value1 = selector1.apply(json);
        Object value2 = selector2.apply(json);

        // Assert
        assertInstanceOf(String.class, value0);
        assertEquals("Bachelor of Science", value0);

        assertInstanceOf(String.class, value1);
        assertEquals("Bachelor of Arts", value1);

        assertInstanceOf(String.class, value2);
        assertEquals("High School", value2);
    }
}