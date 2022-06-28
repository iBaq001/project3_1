package com.amr.project.webapp.controller;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.service.abstracts.ShowcaseService;
import com.amr.project.service.impl.MailSenderRealizationImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.mail.MailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/test")
//Документация доступна по url: http://localhost:8888/swagger-ui/
//Подробнее про аннотации swagger: https://habr.com/ru/post/536388/

public class TestController {
    private MailSenderRealizationImpl mailSender;

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
