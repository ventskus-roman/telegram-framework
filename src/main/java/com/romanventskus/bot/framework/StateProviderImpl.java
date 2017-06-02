package com.romanventskus.bot.framework;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.states.StartState;

import java.util.HashMap;
import java.util.Map;

/**
 * @author r.ventskus
 */
public class StateProviderImpl implements StateProvider {

    Map<User, State> states = new HashMap<>();

    private OutputChannel outputChannel;

    public StateProviderImpl(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    @Override
    public State get(User user) {
        State state = states.get(user);
        if (state == null) {
            state = new StartState(outputChannel, this);
        }
        return state;
    }

    @Override
    public void setNewState(User user, State state) {
        states.put(user, state);
    }
}
