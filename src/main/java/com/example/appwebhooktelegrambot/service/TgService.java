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
    private final AllTablePriceService allTablePriceService;
    private final CarTipsService carTipsService;
    private final LizingCalculatorService lizingCalculatorService;
    private final OnlineApplicationService onlineApplicationService;

    String step = "1";

    public void updateWait(Update update) throws FileNotFoundException {

        TelegramUser telegramUser = webhookService.checkUserWithChatId(update);
        System.out.println(telegramUser);

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                String chatId = String.valueOf(update.getMessage().getChatId());
                int userCurrentStep = telegramUser.getStep() == null ? 1 : telegramUser.getStep();
                if (userCurrentStep > 100) {
                    switch (userCurrentStep) {
                        case 101:
                            switch (text) {
                                case "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25":
                                    aksiyalarService.getHotAugust(update);
                                    break;
                                case "5 yilgacha kredit":
                                    aksiyalarService.getFiveYearsCredit(update);
                                    break;
                                case "Ko'pchilik orzu qilgan NX4e krossover yangi narxlari":
                                    aksiyalarService.getNewPriceNX4e(update);
                                    break;
                                case "Orzungizdagi avtomobil 3 ta to'lovda!":
                                    aksiyalarService.getDreamCarThreeYear(update);
                                    break;
                                case "Elantra va Sonata 30 oygacha bo'lib to'lash":
                                    aksiyalarService.getCarsThirtyMonths(update);
                                    break;
                                case "Yangi Elantra bo'lib to'lash, 40% oldindan to'lov":
                                    aksiyalarService.getCarPayBeforeFortyPercent(update);
                                    break;
                                case "SONATA bo‘lib to‘lash, 40% oldindan to'lov":
                                    aksiyalarService.getPayInSonataInstallments(update);
                                    break;
                                case "Elantra yozi":
                                    aksiyalarService.getElantiraSummer(update);
                                    aksiyalarService.getElantiraSummerInlineButton(chatId);
                                    telegramUser.setStep(StepConstants.STEP_ALL_TIMES);
                                    break;
                                case "⬅️ Орқага":
                                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                                    telegramUser.setStep(null);
                                    break;
                            }
                            break;
                        case 102:
                            switch (text) {
                                case "⬅️ Орқага":
                                    aksiyalarService.getAksiyalar(update);
                                    telegramUser.setStep(StepConstants.STEP_PROMOTION);
                                    break;
                            }
                            break;
                    }
                } else if (userCurrentStep > 200) {

                } else {

                }
                switch (text) {
                    case "/start":
                    case "\uD83C\uDF10 Тилни танлаш":
                        webhookService.whenStart(update);
                        break;
                    case "/Menu":
                    case "\uD83C\uDFE0 Бош меню":
                        webhookService.getAllMenus(update, telegramUser.getLanguage());
                        telegramUser.setStep(null);
                        break;
                    case "Trade-In":
                        trade_inService.getTradeIn(update);
                        break;
                    case "Aksiyalar":
                        aksiyalarService.getAksiyalar(update);
                        telegramUser.setStep(StepConstants.STEP_PROMOTION);
                        break;
                    case "Шартнома учун онлайн ариза":
                        onlineApplicationService.getOnlineApplication(update);
                        break;
                    case "Лизинг калькулятори":
                        lizingCalculatorService.getLizingCalculator(update);
                        break;
                    case "Автомобил турлари":
                        carTipsService.getCarTips(update);
                        break;
                    case "Нархлар жадвали":
                        allTablePriceService.getAllTablePrices(update);
                        break;

                    default:
                        if (step.equals("1")) {
//                            webhookService.getDefaultAnswers(update);
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
                case "\uD83C\uDF10 Тилни танлаш":
                    webhookService.changeLanguageService(update, telegramUser);
                    break;
            }
        }
    }
}
