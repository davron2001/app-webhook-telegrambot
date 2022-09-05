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
                long chatId = update.getMessage().getChatId();
                if (checkCode(text)) {
                    webhookService.checkUserCode(update);
                }
                switch (text) {
                    case "/start":
                        webhookService.whenStart(update);
                        break;
                    case "/Menu":
                    case "\uD83C\uDFE0 Бош меню":
                        webhookService.getAllMenus(update);
                        break;
                    case "Trade-In":
                        webhookService.getTradeIn(chatId, "Hello", "static/images/book.png");
                        break;
                    case "Aksiyalar":
                        webhookService.getAksiyalar(update);
                        break;
                    default:
                        webhookService.getDefaultAnswers(update);
                        break;
                }
//            } else if (update.getMessage().hasContact()) {
//                webhookService.checkPhoneNumber(update);
            }
        }


//         Kelgan callBacklarni ushlash uchun.
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            switch (data) {
                case "Trade-In":

                    break;
                case "code":
                    webhookService.enterYourCode(update);
                    break;
                case "reSend":
                    webhookService.checkSendCodeResend(update);
                    break;
                case "cancel":
                    webhookService.shareContactCanceled(update);
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

    public boolean checkCode(String text) {  // bazada tekshiriladi. Bu hozircha example
        if (text.length() != 6) return false;
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) return false;
        }
        return true;
    }
}
