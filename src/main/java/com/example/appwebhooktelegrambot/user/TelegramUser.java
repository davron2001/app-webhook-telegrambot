package com.example.appwebhooktelegrambot.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    String userName;
    String userTelegramId;
    String step;
    Integer paginationIndex;
    String language;
}
