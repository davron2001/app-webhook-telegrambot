package com.example.appwebhooktelegrambot.service;

import com.example.appwebhooktelegrambot.RestConstants;
import com.example.appwebhooktelegrambot.payload.ResultTelegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebhookService {

    private final RestTemplate restTemplate;

    public void whenStart(Update update) {
        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "Muloqot uchun tilni tanlang. \n\nВыбор языка для общения. \n\nChoosing a language for communication.");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("\uD83C\uDDFA\uD83C\uDDFF O'zbekcha");
        inlineKeyboardButton1.setCallbackData("uzb");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("\uD83C\uDDF7\uD83C\uDDFA Русский");
        inlineKeyboardButton2.setCallbackData("ru");
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F English");
        inlineKeyboardButton3.setCallbackData("en");

        List<InlineKeyboardButton> keyboardButtons1 = new ArrayList<>();
        keyboardButtons1.add(inlineKeyboardButton1);
        List<InlineKeyboardButton> keyboardButtons2 = new ArrayList<>();
        keyboardButtons2.add(inlineKeyboardButton2);
        List<InlineKeyboardButton> keyboardButtons3 = new ArrayList<>();
        keyboardButtons3.add(inlineKeyboardButton3);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtons1);
        rowList.add(keyboardButtons2);
        rowList.add(keyboardButtons3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        ResultTelegram o = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);

    }

    public void whenChooseLanguage(Update update) {
        String callBackLanguage = update.getCallbackQuery().getData();
        String chatId;
        String text = "";
        SendMessage sendMessage;
        switch (callBackLanguage) {
            case "uzb":
                chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                text = "\uD83C\uDDFA\uD83C\uDDFF O'zbek tili tanlandi.";
                sendMessage = new SendMessage(chatId, text);
                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
                text = "Telegram yoqilgan telefon raqaminggizni kontakt ko'rinishida jo'nating.\nBuning uchun Telefon raqamimni yuborish tugmasini bosing";
                SendMessage sendMessage1 = new SendMessage(chatId, text);
                ResultTelegram resultTelegram = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage1, ResultTelegram.class);

                shareContact(chatId);

                break;
            case "ru":
                chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                text = "\uD83C\uDDF7\uD83C\uDDFA Был выбран узбекский язык.";
                sendMessage = new SendMessage(chatId, text);
                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
                text = "Отправьте свой номер телефона с поддержкой Telegram в контактной форме.\n" +
                        "Для этого нажмите кнопку Отправить мой номер телефона";
                sendMessage1 = new SendMessage(chatId, text);
                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage1, ResultTelegram.class);
                break;
            case "en":
                chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
                text = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F The Uzbek language was selected.";
                sendMessage = new SendMessage(chatId, text);
                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
                text = "Send your Telegram-enabled phone number in the contact form.\n" +
                        "To do this, click the Send my phone number button";
                sendMessage1 = new SendMessage(chatId, text);
                restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage1, ResultTelegram.class);
                break;
            default:

                break;


        }
    }

    public void shareContact(String chatId) {

        SendMessage sendMessage = new SendMessage(chatId, "Checking your phone!!!");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRowF = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();

        keyboardButton.setText("Share contact");
        keyboardButton.setRequestContact(true);

        keyboardRowF.add(keyboardButton);
        keyboardRowList.add(keyboardRowF);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
    }

    public void checkPhoneNumber(Update update) {
        List<String> numbers = new ArrayList<>();
        numbers.add("+998914748739");
        numbers.add("+998935017758");
        String phoneNumber = update.getMessage().getContact().getPhoneNumber();
        SendMessage sendMessage = new SendMessage();
        String chatId = String.valueOf(update.getMessage().getChatId());
        sendMessage.setChatId(chatId);
        if (numbers.contains(phoneNumber)) {
            String textForCode = "\uD83D\uDC49 Biz sizga (" + phoneNumber.substring(0, 6) + "****" + phoneNumber.substring(10) + ") SMS orqali kod yubordik.\n\nQuyidagi tugamani bosing va raqaminggizni tasdiqlang \uD83D\uDC47";
            sendMessage.setText(textForCode);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText("\uD83D\uDD22 Kodni kritish");
            inlineKeyboardButton1.setCallbackData("code");
            InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
            inlineKeyboardButton2.setText("\uD83E\uDD14 SMS xabar kelmadi.");
            inlineKeyboardButton2.setCallbackData("reSend");
            InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
            inlineKeyboardButton3.setText("❌ Bekor qilish.");
            inlineKeyboardButton3.setCallbackData("cancel");

            List<InlineKeyboardButton> keyboardButtons1 = new ArrayList<>();
            keyboardButtons1.add(inlineKeyboardButton1);
            List<InlineKeyboardButton> keyboardButtons2 = new ArrayList<>();
            keyboardButtons2.add(inlineKeyboardButton2);
            List<InlineKeyboardButton> keyboardButtons3 = new ArrayList<>();
            keyboardButtons3.add(inlineKeyboardButton3);
            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtons1);
            rowList.add(keyboardButtons2);
            rowList.add(keyboardButtons3);
            inlineKeyboardMarkup.setKeyboard(rowList);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        } else {
            sendMessage.setText("Uzr siz botimizdan foydalana olmaysiz!");
        }
        ResultTelegram o = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
        System.out.println(update);
    }

    public void checkUserCode(Update update) {
        List<String> codes = new ArrayList<>();
        codes.add("123456");
        codes.add("123457");
        String code = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        if (codes.contains(code)){
            sendMessage.setText("Muvafaqiyatli ro'yhatdan o'tdingiz.");
        }else{
            sendMessage.setText("Kod xato kritildi.\nIltimos Tekshirib boshidan kriting.");
        }
        ResultTelegram o = restTemplate.postForObject(RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage", sendMessage, ResultTelegram.class);
    }
}





















