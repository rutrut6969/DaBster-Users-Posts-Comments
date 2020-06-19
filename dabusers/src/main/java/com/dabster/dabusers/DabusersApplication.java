package com.dabster.dabusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class DabusersApplication {


    public static void main(String[] args) {
        SpringApplication.run(DabusersApplication.class, args);
    }

}
