package com.example.appwebhooktelegrambot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultTelegram {

    private boolean ok;

    private Message result;
}
