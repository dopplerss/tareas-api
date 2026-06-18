package org.example.tareasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = "org.example.tareasapi")
public class TareasApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TareasApiApplication.class, args);
    }
}