package com.example.appwebhooktelegrambot.controller;

import com.example.appwebhooktelegrambot.service.TgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class WebhookController {

    private final TgService tgService;

    @PostMapping
    public void getAllUpdates(@RequestBody Update update) throws FileNotFoundException {
        tgService.updateWait(update);
    }
}
