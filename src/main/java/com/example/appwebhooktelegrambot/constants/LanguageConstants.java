package com.example.appwebhooktelegrambot.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LanguageConstants {
    public Map<String, String> returnMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("boshMenu_uzb", "Hyundai Auto Asia botiga hush kelibsiz!\n\nHamma menyular");
        map.put("boshMenu_ru", "Добро пожаловать в бот Hyundai Auto!\nГлавное меню");
        map.put("language_uzb", "\uD83C\uDDFA\uD83C\uDDFF O'zbek tili tanlandi.");
        map.put("language_ru", "\uD83C\uDDF7\uD83C\uDDFA Был выбран узбекский язык.");
        return map;
    }
}
