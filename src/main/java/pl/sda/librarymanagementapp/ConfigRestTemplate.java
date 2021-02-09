package pl.sda.librarymanagementapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories
public class ConfigRestTemplate {

    @Bean
    public RestTemplate configurationRestTemplate() {
        return new RestTemplate();
    }

}
