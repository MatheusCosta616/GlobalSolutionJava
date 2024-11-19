package com.fiap.global.espx.gs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SunnyMeter API")
                        .version("1.0")
                        .description("API para gerenciamento do SunnyMeter - Sistema de monitoramento de consumo e geração de energia elétrica")
                        .contact(new Contact()
                                .name("FIAP")
                                .email("suporte@fiap.com.br")
                                .url("https://www.fiap.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
} 