package com.romanventskus.bot.framework;

/**
 * @author r.ventskus
 */
public interface UserStore {
    User get(Sender sender);
}
