package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.constants.PdfFileUrlAndCaptionConstants;
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
public class AllTablePriceService {

    private final RestTemplate restTemplate;
    private final PdfFileUrlAndCaptionConstants pdfFileUrlAndCaptionConstants;

    public void getAllTablePrices(Update update) {
        String path = "C:\\Users\\Davron\\IdeaProjects\\Projects-master\\app-webhook-telegrambot\\files\\";

        String[][] pdfFileNames = pdfFileUrlAndCaptionConstants.getPdfFileNames();

        for (String[] pdfFileName : pdfFileNames) {
            try {
                SendDocumentOwn doc = new SendDocumentOwn(String.valueOf(update.getMessage().getChatId()), pdfFileName[1], path + pdfFileName[0]);
                MultiValueMap<String, Object> body = doc.getRequestBody();
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);

                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendDocument", requestEntity, ResultTelegram.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

//new SendDocument()
//        .setChatId(message.getChatId())
//        .setDocument("contract.pdf", new URL("https://www.okbhmao.ru/download.php?file=12021").openStream());
