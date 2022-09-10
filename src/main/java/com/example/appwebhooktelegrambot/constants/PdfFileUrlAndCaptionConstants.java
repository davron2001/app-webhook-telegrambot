package com.example.appwebhooktelegrambot.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfFileUrlAndCaptionConstants {
    public String[][] getPdfFileNames() {
        return new String[][]{
                {"New Prices.pdf", "Нархлар жадвали - Staria"},
                {"New Prices Creta выгода 16 млн.pdf", "Нархлар жадвали - Creta"},
                {"New Prices Elantra_uzb.pdf", "Нархлар жадвали - Elantra"},
                {"New Prices  Sonata uzb.pdf", "Нархлар жадвали - Sonata"},
                {"Palisade special uz.pdf", "Нархлар жадвали - Palisade"},
                {"Santa Fe new price uzb.pdf", "Нархлар жадвали - Santa Fe"},
                {"Santa Fe Prestige RUS.pdf", "Нархлар жадвали - Santa Fe Prestige"},
                {"Tucson Lifestile_RUS.pdf", "Нархлар жадвали - TUCSON Lifestile RUS"},
                {"Tucson new DC price.pdf", "Нархлар жадвали - Tucson"},
                {"Tucson NX4e new price.pdf", "Нархлар жадвали - Tucson NX4e"}
        };
    }
}
