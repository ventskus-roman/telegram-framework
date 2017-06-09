package com.romanventskus.bot.framework;

/**
 * @author r.ventskus
 */
public interface StateProvider<T extends User> {
    State get(T user);
    void setNewState(T user, State state);
}
