package com.example.iti;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItiApplication.class, args);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                    .title("ITI API")
                    .description("ITI API for backend tests")
                    .version("v1.0.0")
        );
    }

}
