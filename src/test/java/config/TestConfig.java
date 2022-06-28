package config;



import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;

@Configuration
@SpringBootConfiguration
@EntityScan("com.amr.project.*")
@ComponentScan("com.amr.project.*")
@EnableJpaRepositories("com.amr.project.service.repository")
public class TestConfig {
}

