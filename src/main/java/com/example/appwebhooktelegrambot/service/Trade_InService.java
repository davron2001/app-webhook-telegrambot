package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.constants.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.payload.SendDocumentOwn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class Trade_InService {

    private final RestTemplate restTemplate;
    String fileName = "";
    String fileId = null;

    public void getTradeIn(Update update) {
        String fileName = "ariza.pdf";
        fileName = "C:\\Users\\Davron\\IdeaProjects\\Projects-master\\app-webhook-telegrambot\\files\\ariza.pdf";
        try {
            SendDocumentOwn doc = new SendDocumentOwn(String.valueOf(update.getMessage().getChatId()), "Test", fileName);
            MultiValueMap<String, Object> body = doc.getRequestBody();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);

            ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendDocument", requestEntity, ResultTelegram.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


//    public void getTradeIn(Update update) throws FileNotFoundException {
//        String imageName = "trade_in.jpg";
//        fileName = fileId == null ? RestConstants.MY_URL + "/api/attachment?name=" + imageName : fileId;
//
//        String text = "Izlashga vaqt sarflamay foyda bilan mashinangizni yangilashni yoki sotishni xohlaysizmi? Trade-In dasturidan foydalaning!\n" +
//                "\n" +
//                "Trade-In dasturining tafsilotlarini Toshkentdagi barcha Hyundai dilerlik markazlarida, shuningdek,  yagona call-markazga \uD83D\uDCDE1248 raqamga qo'ngiroq qilib bilib olishingiz mumkin.\n" +
//                "\n" +
//                "*Dastur Toshkent shahrida amal qiladi.";
//        SendPhotoOwn sendPhotoOwn = new SendPhotoOwn(String.valueOf(update.getMessage().getChatId()), text, fileName);
//        ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendPhoto", sendPhotoOwn, ResultTelegram.class);
//        List<PhotoSize> photoSizes = Objects.requireNonNull(resultTelegram).getResult().getPhoto();
//        PhotoSize photoSize = photoSizes.get(photoSizes.size() - 1);
//        fileId = photoSize.getFileId();
//    }
}
