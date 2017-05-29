package com.romanventskus.telegram.framework;

import com.romanventskus.telegram.framework.channel.InputChannel;
import com.romanventskus.telegram.framework.channel.OutputChannel;

public class Framework {

    private InputChannel inputChannel;
    private OutputChannel outputChannel;
    private StateProvider stateProvider;

    public Framework(InputChannel inputChannel, OutputChannel outputChannel, StateProvider stateProvider) {
        this.inputChannel = inputChannel;
        this.outputChannel = outputChannel;
        this.stateProvider = stateProvider;
    }

    public void start() {
        inputChannel.listen(this::process);
    }

    private void process(Message message) {
        State state = stateProvider.get(message.getUser());
        State newState = state.process(message);
        stateProvider.setNewState(message.getUser(), newState);
        //process new state if change
        if (state != newState) {
            process(message);
        }
    }
}
