package br.com.erudio.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("REST API with Spring Boot 3 and Java ")
                .version("v1")
                .description("A REST application for Spring Boot studies")
                .termsOfService("")
                .license(new License()
                        .name("Apache 2.0")
                        .url("")));
    }
}
