package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.Framework;
import com.romanventskus.bot.framework.StateProvider;
import com.romanventskus.bot.framework.UserStore;
import com.romanventskus.bot.framework.configuration.TelegramRealApiImpl;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import lombok.Getter;

/**
 * Created by romanventskus on 29.05.17.
 */
@Getter
public abstract class TelegramFramework {

    static {
        ApiContextInitializer.init();
    }

    private TelegramInputChannel inputChannel;
    private TelegramOutputChannel outputChannel;

    public TelegramFramework(String userName, String token, StateProvider stateProvider, UserStore userStore) {
        assert userName != null;
        assert token != null;
        assert stateProvider != null;
        assert userStore != null;

        try {
            inputChannel = new TelegramInputChannel();

            TelegramRealApi telegramRealApi = getTelegramRealApi(userName, token);

            outputChannel = new TelegramOutputChannel(telegramRealApi);
            userStore = getUserStore();
            Framework framework = new Framework(inputChannel,
                    outputChannel, stateProvider, userStore);
            framework.start();

        } catch (TelegramApiRequestException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract UserStore getUserStore();

    private TelegramRealApi getTelegramRealApi(String userName, String token) throws TelegramApiRequestException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TelegramRealApi telegramRealApi = new TelegramRealApiImpl(inputChannel, userName, token);
        telegramBotsApi.registerBot(telegramRealApi);
        return telegramRealApi;
    }

}
