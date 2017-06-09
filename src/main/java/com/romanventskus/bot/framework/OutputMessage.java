package com.romanventskus.bot.framework;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputMessage {

    private User recipient;
    private String text;
    private ReplyKeyboard replyKeyboard;

}
