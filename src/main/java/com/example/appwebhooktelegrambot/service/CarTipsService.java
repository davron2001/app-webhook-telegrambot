package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.ButtonArraysConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarTipsService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ButtonArraysConstants buttonArrays;
    SendMessage sendMessage = new SendMessage();

    public void getCarTips(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Моделни танланг ⬇️");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);


        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Creta"));
        keyboardRow1.add(new KeyboardButton("Elantra"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Sonata"));
        keyboardRow2.add(new KeyboardButton("Tucson"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("Sonata"));
        keyboardRow3.add(new KeyboardButton("Tucson"));

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add(new KeyboardButton("Sonata"));
        keyboardRow4.add(new KeyboardButton("Tucson"));

        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow5.add(new KeyboardButton("Sonata"));
        keyboardRow5.add(new KeyboardButton("Tucson"));

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(
                keyboardRow1, keyboardRow2, keyboardRow3, keyboardRow4,
                keyboardRow5, keyboardRow11));

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}
