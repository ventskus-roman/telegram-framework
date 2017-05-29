package com.romanventskus.telegram.framework.channel;

import com.romanventskus.telegram.framework.Message;

import java.util.function.Consumer;

/**
 * @author r.ventskus
 */
public interface InputChannel {

    void listen(Consumer<Message> process);
}
