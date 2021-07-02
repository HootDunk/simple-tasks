package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hello World")
@Controller("/hello")
public class HelloWorldController {

    private HelloWorldService service;
    private final GreetingConfig config;

    public HelloWorldController(final HelloWorldService service, final GreetingConfig config) {
        this.service = service;
        this.config = config;
    }

    @Operation(summary = "a greeting")
    @Get("/")
    public String index() {
        return service.sayHi();
    }
    @Operation(summary = "a greeting in english")
    @Get("/en")
    public String greetingInEnglish() {
        return config.getEn();
    }
    @Operation(summary = "a greeting in german")
    @Get("/de")
    public String greetingInGerman() {
        return config.getDe();
    }
}
