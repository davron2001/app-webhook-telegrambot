package com.example.appwebhooktelegrambot.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ButtonArraysConstants {

    public String[][] getAllMenuButtons() {
        return new String[][]{
                {"Trade-In", "Aksiyalar"},
                {"Shartnoma uchun online ariza", "Lizing kalkulyator"},
                {"Avtomabil turlari", "Narxlar jadvali"},
                {"Test drayverga ro'yhatdan o'tish", "TX ro'yhatdan o'tish"},
                {"Kompaniya haqida", "Kontaktlar"},
                {"Bog'lanish"},
                {"\uD83C\uDF10 Tilni tanlash"}
        };
    }

    public String[][] getAksiyalarArray() {
        return new String[][]{
                {"Qaynoq avgust narxlarni eritmoqda\uD83D\uDD25"},
                {"5 yilgacha kredit"},
                {"Ko'pchilik orzu qilgan NX4e krossover yangi narxlari"},
                {"Orzungizdagi avtomobil 3 ta to'lovda!"},
                {"Elantra va Sonata 30 oygacha bo'lib to'lash"},
                {"Yangi Elantra bo'lib to'lash, 40% oldindan to'lov"},
                {"SONATA bo‘lib to‘lash, 40% oldindan to'lov"},
                {"Elantra yozi"},
                {"Hozir oling, keyin to'lang!"},
                {"Лизинг"},
                {"\uD83C\uDFE0 Бош меню"}
        };
    }

    public String[][] carTypes_uzb() {
        return new String[][]{
                {"Elantra"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    public String[][] elantraSummerCarTypes_uzb() {
        return new String[][]{
                {"Base Plus", "Eleganse Plus"},
                {"Style", "Luxe"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    public String[][] stepCarPayLifeTime_uzb() {
        return new String[][]{
                {"13"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    public String[][] stepCarPercent_uzb() {
        return new String[][]{
                {"40", "50"},
                {"60", "70"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    //OnlineApplicationService buttons
    public String[][] getAutoSalonNames() {
        return new String[][]{
                {"Hyundai Toshkent City", "Hyundai janubiy"},
                {"Hyundai Yunisabod", "Hyundai Sergeli"},
                {"Hyundai Urganch", "Hyundai Buxoro"},
                {"Hyundai Samarqand", "Hyundai Namangan"},
                {"Hyundai Fag'ona", "Hyundai Andijon"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    public String[][] getSourceNames() {
        return new String[][]{
                {"Instagram", "Facebook"},
                {"Telegram kanal", "Avtoelon.uz"},
                {"SMS habar", "Telegram habar"},
                {"Banner", "Led monitor"},
                {"Televizor", "Radio"},
                {"Prom.uz", "Olx.uz"},
                {"⬅️ Orqaga", "\uD83C\uDFE0 Bosh menyu"}
        };
    }

    public String[][] example() {
        return new String[][]{
                {"", ""},
                {"", ""}
        };
    }
}
