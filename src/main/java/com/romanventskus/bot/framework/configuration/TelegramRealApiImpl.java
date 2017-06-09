package com.romanventskus.bot.framework.configuration;

import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.Sender;
import com.romanventskus.bot.framework.telegram.TelegramInputChannel;
import com.romanventskus.bot.framework.telegram.TelegramRealApi;

import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by romanventskus on 01.06.17.
 */
public class TelegramRealApiImpl extends TelegramRealApi {

    private TelegramInputChannel inputChannel;
    private String botUsername;
    private String botToken;

    public TelegramRealApiImpl(TelegramInputChannel inputChannel, String botUsername, String botToken) {
        assert inputChannel != null;
        assert botUsername != null;
        assert botToken != null;

        this.inputChannel = inputChannel;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = new Message(new Sender(Long.valueOf((long)update.getMessage().getFrom().getId().intValue())), update.getMessage().getText());
        inputChannel.getConsumer().accept(message);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
