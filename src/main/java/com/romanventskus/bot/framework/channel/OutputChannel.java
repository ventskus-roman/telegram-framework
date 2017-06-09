package com.romanventskus.bot.framework.channel;

import com.romanventskus.bot.framework.OutputMessage;

/**
 * @author r.ventskus
 */
public interface OutputChannel {

    void send(OutputMessage message);
}
