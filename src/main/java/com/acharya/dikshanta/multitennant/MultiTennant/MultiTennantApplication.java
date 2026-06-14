package com.acharya.dikshanta.multitennant.MultiTennant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MultiTennantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTennantApplication.class, args);
    }

}
