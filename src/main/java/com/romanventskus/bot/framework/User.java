package com.romanventskus.bot.framework;

/**
 * @author r.ventskus
 */
public interface User {

    Long getId();

    boolean equals(Object o);

    int hashCode();
}
