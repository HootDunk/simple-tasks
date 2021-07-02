package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@OpenAPIDefinition(
        info = @Info(
                title = "simple tasks",
                version = "0.0",
                description = "A small API to get familiarize myself with micronaut",
                license = @License(name = "MIT")
        )
)
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) { Micronaut.run(Application.class); }
}
