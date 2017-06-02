package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.Framework;
import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.StateProvider;
import com.romanventskus.bot.framework.StateProviderImpl;
import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.configuration.TelegramOutputChannel;
import com.romanventskus.bot.framework.configuration.TelegramRealApiImpl;
import lombok.Getter;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

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
    private StateProvider stateProvider;

    public TelegramFramework(String userName, String token) {
        assert userName != null;
        assert token != null;

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            inputChannel = new TelegramInputChannel();
            TelegramRealApi telegramRealApi = new TelegramRealApiImpl(inputChannel, userName, token);
            outputChannel = new TelegramOutputChannel(telegramRealApi);
            stateProvider = getStateProvider(outputChannel);
            Framework framework = new Framework(inputChannel,
                    outputChannel, stateProvider);
            framework.start();
            telegramBotsApi.registerBot(telegramRealApi);
        } catch (TelegramApiRequestException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract StateProvider getStateProvider(OutputChannel outputChannel);

}
