package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.User;
import com.romanventskus.bot.framework.channel.OutputChannel;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by romanventskus on 01.06.17.
 */
public class TelegramOutputChannel implements OutputChannel {

    private TelegramRealApi telegramRealApi;

    public TelegramOutputChannel(TelegramRealApi telegramRealApi) {
        assert  telegramRealApi != null;

        this.telegramRealApi = telegramRealApi;
    }

    @Override
    public void send(String message, User user) {
        send(message, new ReplyKeyboardMarkup(), user);
    }

    public void send(String message, ReplyKeyboard markup, User user) {
        try {
            telegramRealApi.send((TelegramUser) user, message, markup);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
