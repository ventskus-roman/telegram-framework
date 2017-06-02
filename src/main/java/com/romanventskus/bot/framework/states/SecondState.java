package com.romanventskus.bot.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.State;
import com.romanventskus.bot.framework.StateProvider;
import com.romanventskus.bot.framework.questions.Question;

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
        outputChannel.send("Second state", message.getUser());
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
