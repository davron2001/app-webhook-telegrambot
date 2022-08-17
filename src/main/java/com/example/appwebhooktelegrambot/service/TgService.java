package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TgService {
    private final WebhookService webhookService;

    public void updateKutish(Update update) {

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                if (checkCode(text)){
                    webhookService.checkUserCode(update);
                }
                switch (text) {
                    case "/start":
                        webhookService.whenStart(update);
                        break;
                }
            } else if (update.getMessage().hasContact()) {
                webhookService.checkPhoneNumber(update);
            }
        }

        if (update.hasCallbackQuery()) {
            webhookService.whenChooseLanguage(update);
        }
    }
    public boolean checkCode(String text){
        if (text.length()!=6) return false;
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) return false;
        }
        return true;
    }
}
