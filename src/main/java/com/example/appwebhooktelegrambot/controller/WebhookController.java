package com.example.appwebhooktelegrambot.controller;

import com.example.appwebhooktelegrambot.service.TgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class WebhookController {

    private final TgService tgService;

    String token = "5715347948:AAHnCM1k1NVKZcdS7Zoye8dnfYr4m2ufYi8";

    @PostMapping
    public void getKetmon(@RequestBody Update update) {
        tgService.updateKutish(update);
        }
}
