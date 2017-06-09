package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.OutputMessage;
import com.romanventskus.bot.framework.User;
import com.romanventskus.bot.framework.channel.OutputChannel;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
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
    public void send(OutputMessage message) {
        try {
            User recipient = message.getRecipient();
            String text = message.getText();
            ReplyKeyboard replyKeyboard = message.getReplyKeyboard();

            telegramRealApi.send(recipient, text, replyKeyboard);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
