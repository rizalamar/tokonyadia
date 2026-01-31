package com.enigmacamp.todonyadia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodonyadiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodonyadiaApplication.class, args);
    }

}
