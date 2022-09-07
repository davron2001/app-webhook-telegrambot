package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.constants.StepConstants;
import com.example.appwebhooktelegrambot.user.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class TgService {
    private final WebhookService webhookService;
    private final Trade_InService trade_inService;
    private final AksiyalarService aksiyalarService;

    String step = "1";

    public void updateWait(Update update) throws FileNotFoundException {

        TelegramUser telegramUser = webhookService.checkUserWithChatId(update);
        System.out.println(telegramUser);

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                String chatId = String.valueOf(update.getMessage().getChatId());
                switch (step) {
                    case "ARIZA":
                        webhookService.enterFullName(update);
                        break;
                }
                switch (text) {
                    case "/start":
                        webhookService.whenStart(update);
                        break;
                    case "/Menu":
                    case "\uD83C\uDFE0 Бош меню":
                        webhookService.getAllMenus(update, telegramUser.getLanguage());
                        break;
                    case "Trade-In":
                        trade_inService.getTradeIn(update);
                        break;
                    case "Aksiyalar":
                        aksiyalarService.getAksiyalar(update);
                        break;
                    case "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25":
                        aksiyalarService.getHotAugust(update);
                        break;
                    case "Шартнома учун онлайн ариза":
                        webhookService.getOnlineAriza(update);
                        step = StepConstants.SHARTNOMA_ARIZA;
                        break;
                    case "Лизинг калькулятори":
                        webhookService.getLizingCalculator(update);
                        break;
                    case "Автомобил турлари":
                        webhookService.getCarTips(update);
                        break;
                    case "Нархлар жадвали":
                        webhookService.getAllTablePrices(update);
                        break;

                    case "\uD83C\uDF10 Тилни танлаш":
                        webhookService.getCarTips(update);
                        break;

                    default:
                        if (step.equals("1")) {
                            webhookService.getDefaultAnswers(update);
                        }
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
                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                    break;
            }
        }
    }
}
