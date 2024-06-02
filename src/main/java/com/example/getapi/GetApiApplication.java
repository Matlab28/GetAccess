package com.example.getapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetApiApplication.class, args);
    }

}
