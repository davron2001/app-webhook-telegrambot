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
    private final ActionsService actionsService;
    private final AllTablePriceService allTablePriceService;
    private final CarTipsService carTipsService;
    private final LizingCalculatorService lizingCalculatorService;
    private final OnlineApplicationService onlineApplicationService;

    String step = "1";

    public void updateWait(Update update) throws FileNotFoundException {
        System.out.println(update);

        TelegramUser telegramUser = webhookService.checkUserWithChatId(update);
        System.out.println(telegramUser);

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String text = update.getMessage().getText();
                if (text.equals("\uD83C\uDFE0 Bosh menyu")) {
                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                    telegramUser.setStep(1);
                } else {
                    if (text.equals("⬅️ Orqaga")) {
                        telegramUser.setStep(telegramUser.getStep() - 1);
                    }
                    String chatId = String.valueOf(update.getMessage().getChatId());
                    int userCurrentStep = telegramUser.getStep() == null ? 1 : telegramUser.getStep();
                    if (userCurrentStep > 199) {
                        switch (userCurrentStep) {
                            case 200:
                                if (text.equals("⬅️ Orqaga")) {
                                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                                    telegramUser.setStep(1);
                                }
                                break;
                            case 201:
                                switch (text) {
                                    case "Hyundai Toshkent City":
                                    case "Hyundai janubiy":
                                    case "Hyundai Yunisabod":
                                        onlineApplicationService.enterFullName(update);
                                        telegramUser.setStep(StepConstants.STEP_FULL_NAME);
                                        break;
                                    case "⬅️ Orqaga":
                                        onlineApplicationService.getOnlineApplication(update);
                                        break;
                                }
                                break;
                            case 202:
                                switch (text) {
                                    case "⬅️ Orqaga":
                                        onlineApplicationService.enterFullName(update);
                                        break;
                                    default:
                                        onlineApplicationService.getWhatSourceDidYouNow(update);
                                        telegramUser.setStep(StepConstants.STEP_WHAT_SOURCE_DID_YOU_NOW);
                                        break;
                                }
                                break;
                            case 203:
                                switch (text) {
                                    case "Instagram":
                                        onlineApplicationService.sendPhoneNumber(update);
                                        break;
                                    case "⬅️ Orqaga":
                                        onlineApplicationService.getWhatSourceDidYouNow(update);
                                        break;
                                    default:
                                        onlineApplicationService.getWhatSourceDidYouNow(update);
                                        telegramUser.setStep(StepConstants.STEP_PHONE_NUMBER);
                                        break;
                                }
                                break;
                            case 204:
                                switch (text) {
                                    case "Instagram":
                                        onlineApplicationService.sendPhoneNumber(update);
                                        break;
                                }
                                break;
                            default:
                                webhookService.getDefaultAnswers(update);
                                break;
                        }
                    }
                    else if (userCurrentStep > 99) {
                        switch (userCurrentStep) {
                            case 100:
                                switch (text) {
                                    case "⬅️ Орқага":
                                        webhookService.getAllMenus(update, telegramUser.getLanguage());
                                        telegramUser.setStep(1);
                                        break;
                                }
                            case 101:
                                switch (text) {
                                    case "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25":
                                        actionsService.getHotAugust(update);
                                        break;
                                    case "5 yilgacha kredit":
                                        actionsService.getFiveYearsCredit(update);
                                        break;
                                    case "Ko'pchilik orzu qilgan NX4e krossover yangi narxlari":
                                        actionsService.getNewPriceNX4e(update);
                                        break;
                                    case "Orzungizdagi avtomobil 3 ta to'lovda!":
                                        actionsService.getDreamCarThreeYear(update);
                                        break;
                                    case "Elantra va Sonata 30 oygacha bo'lib to'lash":
                                        actionsService.getCarsThirtyMonths(update);
                                        break;
                                    case "Yangi Elantra bo'lib to'lash, 40% oldindan to'lov":
                                        actionsService.getCarPayBeforeFortyPercent(update);
                                        break;
                                    case "SONATA bo‘lib to‘lash, 40% oldindan to'lov":
                                        actionsService.getPayInSonataInstallments(update);
                                        break;
                                    case "Elantra yozi":
                                        actionsService.getElantiraSummer(update);
                                        actionsService.getElantiraSummerInlineButton(chatId);
                                        telegramUser.setStep(StepConstants.STEP_ALL_TIMES);
                                        break;
                                    case "⬅️ Орқага":
                                        webhookService.getAllMenus(update, telegramUser.getLanguage());
                                        telegramUser.setStep(1);
                                        break;
                                }
                                break;
                            case 102:
                                switch (text) {
                                    case "⬅️ Orqaga":
                                        actionsService.getAksiyalar(update);
                                        telegramUser.setStep(StepConstants.STEP_PROMOTION);
                                        break;
                                }
                                break;
                            case 103:
                                switch (text) {
                                    case "Elantra":
                                        actionsService.getCarTypes(update);
                                        telegramUser.setStep(StepConstants.STEP_CAR_TYPE);
                                        break;
                                    case "⬅️ Orqaga":
                                        actionsService.getCarModel(update);
                                        break;
                                }
                                break;
                            case 104:
                                switch (text) {
                                    case "Base Plus":
                                    case "Eleganse Plus":
                                    case "Style":
                                    case "Luxe":
                                        actionsService.getStepCarLifeTime(update);
                                        telegramUser.setStep(StepConstants.STEP_CAR_PAY_LIFETIME);
                                        break;
                                    case "⬅️ Orqaga":
                                        actionsService.getCarTypes(update);
                                        break;
                                }
                                break;
                            case 105:
                                switch (text) {
                                    case "13":
                                        actionsService.getStepCarPercent(update);
                                        telegramUser.setStep(StepConstants.STEP_CAR_PAY_INTEREST);
                                        break;
                                    case "⬅️ Orqaga":
                                        actionsService.getStepCarLifeTime(update);
                                        break;
                                }
                            case 106:
                                switch (text) {
                                    case "40":
                                    case "50":
                                    case "60":
                                    case "70":
                                        actionsService.getLastAllInformationAboutPayment(update);
                                        telegramUser.setStep(1);
                                        webhookService.getAllMenus(update, telegramUser.getLanguage());
                                        break;
                                    case "⬅️ Orqaga":
                                        break;
                                }
                                break;
                            default:
                                if (text.equals("\uD83C\uDFE0 Bosh menyu")) {
                                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                                    telegramUser.setStep(1);
                                }
                                break;
                        }
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
                            actionsService.getAksiyalar(update);
                            telegramUser.setStep(StepConstants.STEP_PROMOTION);
                            break;
                        case "Shartnoma uchun online ariza":
                            onlineApplicationService.getOnlineApplication(update);
                            telegramUser.setStep(StepConstants.STEP_AUTO_SALONS);
                            break;
                        case "Lizing kalkulyator":
                            lizingCalculatorService.getLizingCalculator(update);
                            break;
                        case "Avtomabil turlari":
                            carTipsService.getCarTips(update);
                            break;
                        case "Narxlar jadvali" :
                            allTablePriceService.getAllTablePrices(update);
                            break;
                        default:
//                            webhookService.getDefaultAnswers(update);
                            break;
                    }
                }
            }

            if (update.getMessage().hasContact()) {
                if (telegramUser.getStep() == 204) {
                    onlineApplicationService.sendMessageAndAllMenu(update);
                    webhookService.getAllMenus(update, telegramUser.getLanguage());
                    telegramUser.setStep(1);
                }
            }
        }

//         Kelgan callBacklarni ushlash uchun.
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            switch (data) {
                case "Trade-In":

                    break;
                case "chooseModel":
                    actionsService.getCarModel(update);
                    telegramUser.setStep(StepConstants.STEP_CAR);
                    break;
                case "uzb":
                case "ru":
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