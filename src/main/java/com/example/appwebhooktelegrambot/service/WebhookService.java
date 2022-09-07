package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.config.BotConfig;
import com.example.appwebhooktelegrambot.constants.LanguageConstants;
import com.example.appwebhooktelegrambot.constants.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import com.example.appwebhooktelegrambot.payload.SendPhotoOwn;
import com.example.appwebhooktelegrambot.user.TelegramUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WebhookService {

    List<TelegramUser> userList = new ArrayList<>();
    private final RestTemplate restTemplate;
    private final LanguageConstants languageConstants;
    private final BotConfig botConfig;
    SendMessage sendMessage = new SendMessage();

    public TelegramUser checkUserWithChatId(Update update) {
        String chatId = "";
        if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }
        if (chatId.length() > 4) {
            for (TelegramUser user : userList) {
                if (user.getUserTelegramId().equals(chatId)) {
                    return user;
                }
            }
            TelegramUser telegramUser = new TelegramUser(update.getMessage().getChat().getFirstName(), chatId, "language", 0, "");
            userList.add(telegramUser);
            return telegramUser;
        } else {

            return new TelegramUser();
        }

    }

    public void whenStart(Update update) {
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Muloqot uchun tilni tanlang. \n\nВыбор языка для общения.");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton("\uD83C\uDDFA\uD83C\uDDFF O'zbekcha");
        inlineKeyboardButton1.setCallbackData("uzb");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton("\uD83C\uDDF7\uD83C\uDDFA Русский");
        inlineKeyboardButton2.setCallbackData("ru");

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>(List.of(
                new ArrayList<>(List.of(inlineKeyboardButton1)),
                new ArrayList<>(List.of(inlineKeyboardButton2))));
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        sendSendMessage(sendMessage);
    }

    public void whenChooseLanguage(Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        String choosenLanguage = update.getCallbackQuery().getData();
        String textLang = "";
        if (choosenLanguage.equals("uzb") || choosenLanguage.equals("ru"))
            textLang = languageConstants.returnMap().get("language_" + choosenLanguage);
        else textLang = "Bizda bunday til mavjud emas. \nKerakli tilni tugamasini bosing.";
        for (TelegramUser telegramUser : userList) {
            if (telegramUser.getUserTelegramId().equals(chatId)) {
                telegramUser.setLanguage(choosenLanguage);
            }
        }
        System.out.println(userList.toString());
        sendMessage.setChatId(chatId);
        sendMessage.setText(textLang);
        sendSendMessage(sendMessage);
    }

    public void getAllMenus(Update update, String lang) {
        String chatId = "";
        if (update.hasCallbackQuery()) {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        } else if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
        }
        SendMessage sendMessage = new SendMessage(chatId, languageConstants.returnMap().get("boshMenu_" + lang));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Trade-In"));
        keyboardRow1.add(new KeyboardButton("Aksiyalar"));

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Шартнома учун онлайн ариза"));
        keyboardRow2.add(new KeyboardButton("Лизинг калькулятори"));

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("Автомобил турлари"));
        keyboardRow3.add(new KeyboardButton("Нархлар жадвали"));

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


    public void enterFullName(Update update) {
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Ism sharifingizni kriting.");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("\uD83C\uDFE0 Бош меню"));
        keyboardRow11.add(new KeyboardButton("⬅️ Орқага"));

        List<KeyboardRow> keyboardRowList = new ArrayList<>(List.of(keyboardRow11));

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
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendPhoto", sendPhoto, ResultTelegram.class);
    }
}





















