package com.example.bukkinyulszbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BukkinyulszBackendApplication {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "UTC");
        SpringApplication.run(BukkinyulszBackendApplication.class, args);
    }

}
