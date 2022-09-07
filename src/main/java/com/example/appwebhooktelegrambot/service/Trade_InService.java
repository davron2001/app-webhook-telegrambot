package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.constants.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.payload.SendPhotoOwn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class Trade_InService {

    private final RestTemplate restTemplate;

    String fileName = "";
    String fileId = null;

    public void getTradeIn(Update update) throws FileNotFoundException {
        String imageName = "trade_in.jpg";
        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
        String text = "Izlashga vaqt sarflamay foyda bilan mashinangizni yangilashni yoki sotishni xohlaysizmi? Trade-In dasturidan foydalaning!\n" +
                "\n" +
                "Trade-In dasturining tafsilotlarini Toshkentdagi barcha Hyundai dilerlik markazlarida, shuningdek,  yagona call-markazga \uD83D\uDCDE1248 raqamga qo'ngiroq qilib bilib olishingiz mumkin.\n" +
                "\n" +
                "*Dastur Toshkent shahrida amal qiladi.";
        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
        fileId = photoSize.getFileId();
        System.out.println(resultTelegram);
    }
}
