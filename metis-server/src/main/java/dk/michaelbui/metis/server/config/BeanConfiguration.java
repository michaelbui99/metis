package dk.michaelbui.metis.server.config;

import dk.michaelbui.metis.core.domain.Engine;
import dk.michaelbui.metis.core.domain.MetisEngine;
import dk.michaelbui.metis.core.domain.event.EventsService;
import dk.michaelbui.metis.core.domain.event.MetisEventsService;
import dk.michaelbui.metis.core.domain.rule.MetisRulesService;
import dk.michaelbui.metis.core.domain.rule.RulesRepository;
import dk.michaelbui.metis.core.domain.rule.RulesService;
import dk.michaelbui.metis.plugin.MetisPluginManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.nio.file.Path;

@Configuration
public class BeanConfiguration {
    @Value("${metis.plugin.dir}")
    private String pluginDir;
    @Value("${metis.plugin.config.file}")
    private String pluginsConfigFile;

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

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MetisPluginManager metisPluginManager() {
        MetisPluginManager manager = new MetisPluginManager(Path.of(pluginDir), Path.of(pluginsConfigFile));
        manager.initializePlugins();
        return manager;
    }
}
