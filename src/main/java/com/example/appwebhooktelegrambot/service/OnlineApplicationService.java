package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.ButtonArraysConstants;
import com.example.appwebhooktelegrambot.payload.CreateReplyButtonsOwn;
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
    private final CreateReplyButtonsOwn createReplyButtonsOwn;
    SendMessage sendMessage = new SendMessage();

    public void getOnlineApplication(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Шартнома тузмоқчи бўлган автосалонни танланг ⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArrays.getAutoSalonNames());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void enterFullName(Update update) {
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Ism sharifingizni kriting.");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));
        keyboardRow11.add(new KeyboardButton("⬅️ Orqaga"));

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(keyboardRow11));

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getWhatSourceDidYouNow(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Hyundai botiga qaysi manba orqali o'tdingiz.");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArrays.getSourceNames());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void sendPhoneNumber(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Қуйидаги тугмани босиш орқали контактингизни юборинг ⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton("\uD83D\uDCF1 Raqamni junatish");
        keyboardButton.setRequestContact(true);
        keyboardRow1.add(keyboardButton);
        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));
        keyboardRow11.add(new KeyboardButton("⬅️ Orqaga"));
        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(keyboardRow1, keyboardRow11));
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void sendMessageAndAllMenu(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "✅ Раҳмат. Сиз шартнома тузиш учун аризани муваффақиятли топширдингиз. Менежеримиз тез орада Cиз билан боғланади.");
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }

}
