package com.romanventskus.bot.framework.configuration;

import com.romanventskus.bot.framework.User;
import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.telegram.TelegramRealApi;
import com.romanventskus.bot.framework.telegram.TelegramUser;
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
        try {
            telegramRealApi.send((TelegramUser) user, message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
