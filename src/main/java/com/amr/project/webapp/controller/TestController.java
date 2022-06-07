package com.amr.project.webapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/test")
//Документация доступна по url: http://localhost:8888/swagger-ui/
//Подробнее про аннотации swagger: https://habr.com/ru/post/536388/

public class TestController {
    @GetMapping("")
    @Operation(
            summary = "Тестовый контроллер №1",
            description = "Подробное описание тестрового контроллера №1"
    )
    public String testRest() {
        return "Hello, this is rest controller!";
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Тестовый контроллер №2",
            description = "Подробное описание тестрового контроллера №2"
    )

    public String testRest1(@PathVariable @Parameter(description = "Идентификатор") @Min(0) long id) {
        System.out.println("Тестовый контроллер №2");
        return "id = " + id;
    }
}
