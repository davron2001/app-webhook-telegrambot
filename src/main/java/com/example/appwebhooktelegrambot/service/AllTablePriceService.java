package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.ButtonArraysConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class AllTablePriceService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ButtonArraysConstants buttonArrays;
    SendMessage sendMessage = new SendMessage();

    public void getAllTablePrices(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "PDFlar ⬇️");
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}
