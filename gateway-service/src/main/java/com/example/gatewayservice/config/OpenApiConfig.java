package com.example.gatewayservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API do Serviço de Gateway")
                        .description("Documentação da API do serviço de Gateway")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Integrantes: Esthevan Pereira, Lucas Ramon, Maria Eduarda Maia, Maurício Krziminski")
                                .url("https://github.com/T2-Projarc/task-manager-microservices")));
    }
}