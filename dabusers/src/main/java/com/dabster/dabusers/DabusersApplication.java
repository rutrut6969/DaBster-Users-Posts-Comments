package com.dabster.dabusers;

import com.dabster.dabusers.services.UserAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DabusersApplication {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new UserAuditing();
    }
    public static void main(String[] args) {
        SpringApplication.run(DabusersApplication.class, args);
    }

}
