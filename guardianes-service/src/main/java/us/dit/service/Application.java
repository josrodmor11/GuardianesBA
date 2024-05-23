package us.dit.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import us.dit.service.config.DefaultWebSecurityConfig;

@SpringBootApplication
@Import(DefaultWebSecurityConfig.class)
@EnableJpaRepositories("us.dit.")
@ComponentScan(basePackages = {"us.dit."})
@EntityScan("us.dit.")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}