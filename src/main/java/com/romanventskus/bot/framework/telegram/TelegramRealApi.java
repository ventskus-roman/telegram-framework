package com.romanventskus.bot.framework.telegram;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Getter
@Setter
public abstract class TelegramRealApi extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramRealApi.class);

    @Override
    public abstract String getBotUsername();

    @Override
    public abstract String getBotToken();

    public void send(TelegramUser user, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setText(text);
        sendMessage.setChatId(user.getId());
        sendApiMethod(sendMessage);
    }

}
