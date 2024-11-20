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
                        .description("API para gerenciamento do SunnyMeter - Sistema de monitoramento de consumo e geração de energia elétrica\n\n" +
                                   "Integrantes do Grupo:\n" +
                                   "- Caíque Walter Silva - RM550693\n" +
                                   "- Guilherme Nobre Bernardo - RM98604\n" +
                                   "- Matheus José de Lima Costa - RM551157")
                       );
    }
} 