package com.romanventskus.telegram.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.telegram.framework.Message;
import com.romanventskus.telegram.framework.State;
import com.romanventskus.telegram.framework.StateProvider;
import com.romanventskus.telegram.framework.channel.OutputChannel;
import com.romanventskus.telegram.framework.questions.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

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

    @Override
    public Map<String, Supplier<State>> getCommands() {
        Map<String, Supplier<State>> commands = new HashMap<>();
        commands.put("/back", () -> new StartState(outputChannel, stateProvider));

        return commands;
    }
}
