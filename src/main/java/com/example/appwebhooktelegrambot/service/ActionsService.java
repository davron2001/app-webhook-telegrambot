package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.ButtonArraysConstants;
import com.example.appwebhooktelegrambot.constants.RestConstants;
import com.example.appwebhooktelegrambot.payload.CreateReplyButtonsOwn;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.payload.SendPhotoOwn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ActionsService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ButtonArraysConstants buttonArraysConstants;
    private final CreateReplyButtonsOwn createReplyButtonsOwn;
    SendMessage sendMessage = new SendMessage();
    String fileName = "";
    String fileId = null;

    public void getAksiyalar(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Aksiyani tanlang⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArraysConstants.getAksiyalarArray());

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getHotAugust(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getFiveYearsCredit(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getNewPriceNX4e(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getDreamCarThreeYear(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getCarsThirtyMonths(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getCarPayBeforeFortyPercent(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getPayInSonataInstallments(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.\n" +
                "⠀\n" +
                "Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getElantiraSummer(Update update) throws FileNotFoundException {
        String imageName = "qaynoqAvgust.png";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25\n" +
                "\n" +
                "Yangi aqlli CRETA avtomobilini endi 16 000 000 so‘mgacha foyda bilan xarid qilishingiz mumkin!\n" +
                "⠀\n" +
                "Sizning ixtiyoringizga ko'plab yoqimli xususiyatlar taqdim etilgan: premium toifadagi Bose multimedia tizimi, kalitsiz eshikni ochish va dvigatelni ishga tushirish tugmasi, simsiz zaryadlash qurilmasi va boshqa foydali imkoniyatlar, ularning aksariyati o'z sinfi uchun noyob hisoblanadi.";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }

    public void getElantiraSummerInlineButton(String chatId) {
        sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Krossoverni lizing, muddatli to'lov va 5 yilgacha kreditga ham sotib olishingiz mumkin.\n" +
                "⠀\n" +
                "O‘zbekistondagi barcha Hyundai dilerlik markazlaridan so‘rang.\n" +
                "⠀\n" +
                "Prays va shaxsiy to'lov jadvalini olish uchun:\n" +
                "Yagona aloqa markazi \uD83D\uDCDE: 1248\n" +
                "www.hyundai.com");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton("Elantira yozi");
        inlineKeyboardButton1.setCallbackData("chooseModel");
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>(List.of(
                new ArrayList<>(List.of(inlineKeyboardButton1))));
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getCarModel(Update update) {
        String chatId = "";

        if (update.hasMessage())
            chatId = String.valueOf(update.getMessage().getChatId());
        else if (update.hasCallbackQuery())
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        sendMessage = new SendMessage(chatId, "Моделни танланг ⬇️");
        ReplyKeyboardMarkup replyButtons = createReplyButtonsOwn.createReplyButtons2x2(buttonArraysConstants.carTypes_uzb());
        sendMessage.setReplyMarkup(replyButtons);
        sendSendMessage(sendMessage);
    }

    public void getCarTypes(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Модель: Elantra\n" +
                "\n" +
                "Комплектация: \n" +
                "1. Base Plus - 289 000 000 UZS\n" +
                "2. Elegance Plus - 326 000 000 UZS\n" +
                "3. Style - 348 000 000 UZS\n" +
                "4. Luxe - 385 000 000 UZS\n" +
                "\n" +
                "Комплектацияни танланг ⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArraysConstants.elantraSummerCarTypes_uzb());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getStepCarLifeTime(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Модель: Elantra\n" +
                "Комплектация: Base Plus\n" +
                "Комплектация нархи: 289 000 000 UZS\n" +
                "\n" +
                "Муддатини танланг (ойда) ⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArraysConstants.stepCarPayLifeTime_uzb());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getStepCarPercent(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Модель: Elantra\n" +
                "Комплектация: Base Plus\n" +
                "Комплектация нархи: 289 000 000 UZS\n" +
                "Муддати: 13 ой\n" +
                "\n" +
                "Олдиндан тўлов фоизини танланг ⬇️");

        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtonsOwn.createReplyButtons2x2(buttonArraysConstants.stepCarPercent_uzb());
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getLastAllInformationAboutPayment(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Калькуляция тури: Элантра Ёзи\n" +
                "Модель: Elantra\n" +
                "Комплектация: Base Plus\n" +
                "Комплектация нархи: 289 000 000 UZS\n" +
                "Муддати: 13 ой\n" +
                "\n" +
                "ДАНда рўйхатдан ўтказиш: 1 230 000 UZS\n" +
                "Ёл Жамғармаси: 0 UZS (0%)\n" +
                "Шартнома суммаси: 290 230 000 UZS\n" +
                "\n" +
                "Олдиндан тулов: 174 138 000 UZS\n" +
                "Олдиндан тулов фоизи: 60%\n" +
                "Комиссия: 2 902 300 UZS (1%)\n" +
                "Объектини сугурталаш: 2 191 583 UZS (0.7%)\n" +
                "Олдиндан тулов суммаси: 179 231 883 UZS\n" +
                "\n" +
                "Суммаси: 116 092 000 UZS (40%)\n" +
                "Йиллик фоиз ставкаси: 0%\n" +
                "\n" +
                "Ойлаб тўловлар ⬇️\n" +
                "\n" +
                "1) 8 930 154 UZS\n" +
                "2) 8 930 154 UZS\n" +
                "3) 8 930 154 UZS\n" +
                "4) 8 930 154 UZS\n" +
                "5) 8 930 154 UZS\n" +
                "6) 8 930 154 UZS\n" +
                "7) 8 930 154 UZS\n" +
                "8) 8 930 154 UZS\n" +
                "9) 8 930 154 UZS\n" +
                "10) 8 930 154 UZS\n" +
                "11) 8 930 154 UZS\n" +
                "12) 8 930 154 UZS\n" +
                "13) 8 930 154 UZS\n" +
                "\n" +
                "Жами: 116 092 000 UZS");
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}
