package config;



import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@SpringBootConfiguration
@EntityScan("com.amr.project.*")
@ComponentScan("com.amr.project.*")
@EnableJpaRepositories("com.amr.project.*")
@EnableWebMvc
public class TestConfig {
}

