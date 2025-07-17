package dk.michaelbui.metis.server.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.michaelbui.metis.core.domain.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesExporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RulesExporter.class);
    private final ObjectMapper mapper;

    public RulesExporter() {
        this.mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public byte[] export(List<Rule> rules) {
        try {
            return mapper.writeValueAsBytes(rules);
        } catch (JsonProcessingException e) {
            LOGGER.error("Export of rules failed. Unable to serialize rules: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
