package config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@SpringBootConfiguration
@EntityScan("com.amr.project.*")
@ComponentScan("com.amr.project.*")
@EnableJpaRepositories
public class TestConfig {
}
