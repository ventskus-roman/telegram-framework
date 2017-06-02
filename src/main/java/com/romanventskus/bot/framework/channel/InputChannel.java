package com.romanventskus.bot.framework.channel;

import com.romanventskus.bot.framework.Message;

import java.util.function.Consumer;

/**
 * @author r.ventskus
 */
public interface InputChannel {

    void listen(Consumer<Message> process);
}
