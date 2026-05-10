package com.major.devsolver_backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("DevSolver API")
                                .version("1.0")
                                .description("Backend API for DevSolver - AI-powered developer platform")
                                .contact(
                                        new Contact()
                                                .name("Brajesh Prajapati")
                                                .email("prajapatibrajesh003@gmail.com")
                                )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,

                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
