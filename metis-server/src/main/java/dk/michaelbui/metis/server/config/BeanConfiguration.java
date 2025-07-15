package dk.michaelbui.metis.server.config;

import dk.michaelbui.metis.server.domain.Engine;
import dk.michaelbui.metis.server.domain.RuleRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Engine engine(RuleRepository ruleRepository) {
        return new Engine(ruleRepository);
    }
}
