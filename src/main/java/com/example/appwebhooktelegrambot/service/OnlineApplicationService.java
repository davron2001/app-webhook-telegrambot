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
public class OnlineApplicationService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ButtonArraysConstants buttonArrays;
    SendMessage sendMessage = new SendMessage();

    public void getOnlineApplication(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Шартнома тузмоқчи бўлган автосалонни танланг ⬇️");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);


        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow1.add(new KeyboardButton("Hyundai Tashkent"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow2.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow3.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow4.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow5.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow5.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow6 = new KeyboardRow();
        keyboardRow6.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow6.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow7 = new KeyboardRow();
        keyboardRow7.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow7.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow8 = new KeyboardRow();
        keyboardRow8.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow8.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow9 = new KeyboardRow();
        keyboardRow9.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow9.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow10 = new KeyboardRow();
        keyboardRow10.add(new KeyboardButton("Hyundai Tashkent City"));
        keyboardRow10.add(new KeyboardButton("Hyundai Tashkent City"));

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));
        keyboardRow11.add(new KeyboardButton("⬅️ Орқага"));

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(
                keyboardRow1, keyboardRow2, keyboardRow3, keyboardRow4,
                keyboardRow5, keyboardRow6, keyboardRow7, keyboardRow8,
                keyboardRow9, keyboardRow10, keyboardRow11));

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}
