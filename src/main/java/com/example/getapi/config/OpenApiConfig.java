package com.example.getapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Sign In"
                ),
                description = "OpenApi documentation for Frontend",
                title = "Sign in Project Api",
                version = "1.0",
                termsOfService = "Terms of Service"
        )
)
public class OpenApiConfig {
}
