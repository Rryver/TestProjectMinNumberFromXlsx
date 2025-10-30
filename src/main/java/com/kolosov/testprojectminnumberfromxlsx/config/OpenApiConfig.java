package com.kolosov.testprojectminnumberfromxlsx.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Тестовое задание",
                description = "Поиск N-ного минимального числа в .xlsx файле"
        )
)
public class OpenApiConfig {
}
