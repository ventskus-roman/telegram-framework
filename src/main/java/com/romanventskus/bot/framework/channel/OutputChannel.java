package com.romanventskus.bot.framework.channel;

import com.romanventskus.bot.framework.User;

/**
 * @author r.ventskus
 */
public interface OutputChannel {

    void send(String message, User user);
}
