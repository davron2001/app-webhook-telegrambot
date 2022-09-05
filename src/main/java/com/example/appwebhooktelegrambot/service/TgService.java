package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.user.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class TgService {
    private final WebhookService webhookService;

    String step = "1";

    public void updateWait(Update update) throws FileNotFoundException {

        TelegramUser telegramUser = webhookService.checkUserWithChatId(update);
        step = telegramUser.getStep();

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                String chatId = String.valueOf(update.getMessage().getChatId());
                switch (text) {
                    case "/start":
                        webhookService.whenStart(update);
                        break;
                    case "/Menu":
                    case "\uD83C\uDFE0 Бош меню":
                        webhookService.getAllMenus(update);
                        break;
                    case "Trade-In":
                        webhookService.getTradeIn(update);
                        break;
                    case "Aksiyalar":
                        webhookService.getAksiyalar(update);
                        break;
                    default:
                        webhookService.getDefaultAnswers(update);
                        break;
                }
            }
        }


//         Kelgan callBacklarni ushlash uchun.
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            switch (data) {
                case "Trade-In":

                    break;
                // Aslida step berilsa yetadi shu yerda.
                case "uzb":
                case "ru":
                case "en":
                    webhookService.whenChooseLanguage(update);
                    webhookService.getAllMenus(update);
                    break;
            }
        }
    }
}
