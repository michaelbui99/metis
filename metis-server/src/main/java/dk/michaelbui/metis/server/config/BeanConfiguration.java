package dk.michaelbui.metis.server.config;

import dk.michaelbui.metis.core.domain.Engine;
import dk.michaelbui.metis.core.domain.MetisEngine;
import dk.michaelbui.metis.core.domain.event.EventsService;
import dk.michaelbui.metis.core.domain.event.MetisEventsService;
import dk.michaelbui.metis.core.domain.rule.MetisRulesService;
import dk.michaelbui.metis.core.domain.rule.RulesRepository;
import dk.michaelbui.metis.core.domain.rule.RulesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public EventsService eventsService() {
        return new MetisEventsService();
    }

    @Bean
    public RulesService rulesService(RulesRepository rulesRepository) {
        return new MetisRulesService(rulesRepository);
    }

    @Bean
    public Engine engine(RulesService rulesService, EventsService eventsService) {
        return new MetisEngine(rulesService, eventsService);
    }
}
