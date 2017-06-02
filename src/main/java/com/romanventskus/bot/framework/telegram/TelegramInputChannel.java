package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.channel.InputChannel;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * Created by romanventskus on 29.05.17.
 */
public class TelegramInputChannel implements InputChannel {

    @Getter
    private Consumer<Message> consumer;

    @Override
    public void listen(Consumer<Message> process) {
        this.consumer = process;
    }
}
