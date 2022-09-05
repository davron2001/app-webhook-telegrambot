package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.user.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebhookService {

    List<TelegramUser> userList = new ArrayList<>();

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    SendMessage sendMessage = new SendMessage();

    public TelegramUser checkUserWithChatId(Update update) {
        String chatId = "";
        if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }
        if (chatId.length() > 2) {
            for (TelegramUser user : userList) {
                if (user.getUserTelegramId().equals(chatId)) {
                    return user;
                }
            }
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setUserTelegramId(chatId);
            userList.add(telegramUser);
            return telegramUser;
        } else {
            return new TelegramUser();
        }

    }

    public void whenStart(Update update) {
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Muloqot uchun tilni tanlang. \n\nВыбор языка для общения. \n\nChoosing a language for communication.");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton("\uD83C\uDDFA\uD83C\uDDFF O'zbekcha");
        inlineKeyboardButton1.setCallbackData("uzb");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton("\uD83C\uDDF7\uD83C\uDDFA Русский");
        inlineKeyboardButton2.setCallbackData("ru");
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F English");
        inlineKeyboardButton3.setCallbackData("en");

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>(List.of(
                new ArrayList<>(List.of(inlineKeyboardButton1)),
                new ArrayList<>(List.of(inlineKeyboardButton2)),
                new ArrayList<>(List.of(inlineKeyboardButton3))));
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        sendSendMessage(sendMessage);
    }

    public void whenChooseLanguage(Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        String textLang = "";
        switch (update.getCallbackQuery().getData()) {
            case "uzb":
                textLang = "\uD83C\uDDFA\uD83C\uDDFF O'zbek tili tanlandi.";
                break;
            case "ru":
                textLang = "\uD83C\uDDF7\uD83C\uDDFA Был выбран узбекский язык.";
                break;
            case "en":
                textLang = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F The Uzbek language was selected.";
                break;
            default:
                textLang = "Bizda bunday til mavjud emas. \nKerakli tilni tugamasini bosing.";
                break;
        }
        sendMessage.setChatId(chatId);
        sendMessage.setText(textLang);
        sendSendMessage(sendMessage);
    }

    public void getAllMenus(Update update) {
        String chatId = "";
        if (update.hasCallbackQuery()) {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        } else if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
        }
        SendMessage sendMessage = new SendMessage(chatId, "All menu");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Trade-In"));
        keyboardRow1.add(new KeyboardButton("Aksiyalar"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("\uD83D\uDCB5 To'lov"));
        keyboardRow2.add(new KeyboardButton("\uD83D\uDCB0 Balans"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("\uD83D\uDD00 O'tkazmalar"));
        keyboardRow3.add(new KeyboardButton("\uD83D\uDDD3 To'lov tarixi"));

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add(new KeyboardButton("↘️ Kiruvchi hisoblar"));
        keyboardRow4.add(new KeyboardButton("\uD83D\uDD06 Saralangan to'lovlar"));

        KeyboardRow keyboardRow5 = new KeyboardRow();
        KeyboardButton keyboardButton51 = new KeyboardButton("⚙️ Sozlamalar");
        keyboardRow5.add(keyboardButton51);

        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        keyboardRowList.add(keyboardRow3);
        keyboardRowList.add(keyboardRow4);
        keyboardRowList.add(keyboardRow5);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getTradeIn(Update update) throws FileNotFoundException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(update.getMessage().getChatId()));
        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("D:\\book.png"));
        sendPhoto.setPhoto(inputFile);
        sendSendPhoto(sendPhoto);
    }

    public void getAksiyalar(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage(chatId, "Aksiyani tanlang⬇️");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);


        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("5 yilgacha kredit"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("Ko'pchilik orzu qilgan NX4e krossover yangi narxlari"));

        KeyboardRow keyboardRow4 = new KeyboardRow();
        keyboardRow4.add(new KeyboardButton("Orzungizdagi avtomobil 3 ta to'lovda!"));

        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow5.add(new KeyboardButton("Elantra va Sonata 30 oygacha bo'lib to'lash"));

        KeyboardRow keyboardRow6 = new KeyboardRow();
        keyboardRow6.add(new KeyboardButton("Yangi Elantra bo'lib to'lash, 40% oldindan to'lov"));

        KeyboardRow keyboardRow7 = new KeyboardRow();
        keyboardRow7.add(new KeyboardButton("SONATA bo‘lib to‘lash, 40% oldindan to'lov"));

        KeyboardRow keyboardRow8 = new KeyboardRow();
        keyboardRow8.add(new KeyboardButton("Elantra yozi"));

        KeyboardRow keyboardRow9 = new KeyboardRow();
        keyboardRow9.add(new KeyboardButton("Hozir oling, keyin to'lang!"));

        KeyboardRow keyboardRow10 = new KeyboardRow();
        keyboardRow10.add(new KeyboardButton("Лизинг"));

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(
                keyboardRow1, keyboardRow2, keyboardRow3, keyboardRow4,
                keyboardRow5, keyboardRow6, keyboardRow7, keyboardRow8,
                keyboardRow9, keyboardRow10, keyboardRow11));

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendSendMessage(sendMessage);
    }

    public void getDefaultAnswers(Update update) {
        sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Noto'g'ri so'rov.");
        sendSendMessage(sendMessage);
    }

    public void sendSendMessage(SendMessage sendMessage) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendMessage, ResultTelegram.class);
    }

    public void sendSendPhoto(SendPhoto sendPhoto) {
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", sendPhoto, ResultTelegram.class);
    }
}





















