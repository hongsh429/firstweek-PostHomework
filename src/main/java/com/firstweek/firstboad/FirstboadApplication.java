package com.firstweek.firstboad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FirstboadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstboadApplication.class, args);
    }

}
