package ru.sushchenko.infobez.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Cryptography service",
                description = "Лабораторные работы номер 3-4 по предмету Основы информационной безопасности", version = "1.0.0",
                contact = @Contact(
                        name = "Sushchenko Artyom",
                        email = "artoymsushchenko@gmail.com",
                        url = "https://github.com/lordphiluren"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
