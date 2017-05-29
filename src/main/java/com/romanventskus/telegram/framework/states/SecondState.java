package com.romanventskus.telegram.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.telegram.framework.Message;
import com.romanventskus.telegram.framework.State;
import com.romanventskus.telegram.framework.StateProvider;
import com.romanventskus.telegram.framework.channel.OutputChannel;
import com.romanventskus.telegram.framework.questions.Question;

import java.util.Set;

/**
 * @author r.ventskus
 */
public class SecondState extends State {
    public SecondState(OutputChannel outputChannel, StateProvider stateProvider) {
        super(outputChannel, stateProvider);
    }

    @Override
    public State handle(Message message) {
        outputChannel.send("Second state");
        return this;
    }

    @Override
    public Set<Question> getQuestions() {
        return Sets.newHashSet();
    }
}
