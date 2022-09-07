package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.ButtonArraysConstants;
import com.example.appwebhooktelegrambot.constants.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.payload.SendPhotoOwn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AksiyalarService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ButtonArraysConstants buttonArrays;
    SendMessage sendMessage = new SendMessage();
    String fileName = "";
    String fileId = null;

    public void getAksiyalar(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Aksiyani tanlang⬇️");
        ReplyKeyboardMarkup replyKeyboardMarkup = createReplyButtons(buttonArrays.getAksiyalarArray());

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

    public ReplyKeyboardMarkup createReplyButtons(String[] array) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        for (String s : array) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(s));
            keyboardRowList.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}
