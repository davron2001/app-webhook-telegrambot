package com.example.appwebhooktelegrambot.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    String userTelegramId;
    String step = "1";
    Integer paginationIndex = 0;

    public TelegramUser(String userTelegramId) {
        this.userTelegramId = userTelegramId;
    }

    public TelegramUser(Integer paginationIndex) {
        this.paginationIndex = paginationIndex;
    }
}
