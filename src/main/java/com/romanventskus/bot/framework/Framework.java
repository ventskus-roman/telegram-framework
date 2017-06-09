package com.romanventskus.bot.framework;

import com.romanventskus.bot.framework.channel.InputChannel;
import com.romanventskus.bot.framework.channel.OutputChannel;

public class Framework {

    private InputChannel inputChannel;
    private OutputChannel outputChannel;
    private StateProvider stateProvider;
    private UserStore userStore;

    public Framework(InputChannel inputChannel, OutputChannel outputChannel, StateProvider stateProvider, UserStore userStore) {
        this.inputChannel = inputChannel;
        this.outputChannel = outputChannel;
        this.stateProvider = stateProvider;
        this.userStore = userStore;
    }

    public void start() {
        inputChannel.listen(this::process);
    }

    private void process(Message message) {
        User user = userStore.get(message.getSender());
        State state = stateProvider.get(user);
        state.setOutputChannel(outputChannel);
        State newState = state.process(message, user);
        stateProvider.setNewState(user, newState);
        if (state != newState) {
            state.clear();
            newState.clear();
            process(message);
        }
    }
}
