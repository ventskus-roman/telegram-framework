package com.romanventskus.bot.framework;

/**
 * @author r.ventskus
 */
public interface StateProvider {
    State get(User user);
    void setNewState(User user, State state);
}
