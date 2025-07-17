package dk.michaelbui.metis.plugin.metis.core.domain.condition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.michaelbui.metis.core.domain.condition.EvaluationContext;
import dk.michaelbui.metis.core.domain.condition.GreaterThanCondition;
import dk.michaelbui.metis.core.domain.paramvalue.selector.JsonSelector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreaterThanConditionTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Sunny scenario for '>' comparison where the selected value is number")
    void numberComparisonEqualsNotAllowedShouldPass() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.age"), 17, false);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Sunny scenario for '>' comparison where the selected value is string")
    void stringComparisonEqualsNotAllowedShouldPass() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18,
                    "name": "bob"
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.name"), 2, false);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check equals behaviour for '>' comparison where the selected value is number")
    void numberComparisonEqualsNotAllowedShouldFail() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.age"), 18, false);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Check equals behaviour for '>' comparison where the selected value is string")
    void stringComparisonEqualsNotAllowedShouldFail() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18,
                    "name": "bob"
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.name"), 3, false);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Check equals behaviour for '>=' comparison where the selected value is number")
    void numberComparisonEqualsAllowedShouldPass() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.age"), 18, true);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Check equals behaviour for '>=' comparison where the selected value is string")
    void stringComparisonEqualsAllowedShouldPass() throws JsonProcessingException {
        // Arrange
        String jsonString = """
                {
                    "age": 18,
                    "name": "bob"
                }
                """;
        GreaterThanCondition condition = new GreaterThanCondition(new JsonSelector("$.name"), 3, true);
        EvaluationContext context = new EvaluationContext(objectMapper.readTree(jsonString));

        // Act
        boolean result = condition.evaluate(context);

        // Assert
        assertTrue(result);
    }

}