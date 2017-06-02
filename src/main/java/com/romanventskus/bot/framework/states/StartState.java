package com.romanventskus.bot.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.questions.Question;
import com.romanventskus.bot.framework.State;
import com.romanventskus.bot.framework.StateProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class StartState extends State {

    private Question whatIsYourName = new Question<String>("name", "What is your name?", "Incorrect name, should be only letters", name -> !name.equals("1"));

    private Question whatIsYourAge = new Question<Integer>("age", "What is your age?", "Please, enter a number", age -> {
        try {
            Integer.parseInt(age);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    });

    public StartState(OutputChannel outputChannel, StateProvider stateProvider) {
        super(outputChannel, stateProvider);
    }

    @Override
    public State handle(Message message) {
        if (!whatIsYourName.isAnswered()) {
            ask(whatIsYourName, message.getUser());
            return this;
        }
        if (!whatIsYourAge.isAnswered()) {
            ask(whatIsYourAge, message.getUser());
            return this;
        }
        outputChannel.send("Your name is " + whatIsYourName.getAnswer(), message.getUser());
        outputChannel.send("Your age is " + whatIsYourAge.getAnswer(), message.getUser());
        return new SecondState(outputChannel, stateProvider);
    }

    @Override
    public Set<Question> getQuestions() {
        return Sets.newHashSet(whatIsYourName, whatIsYourAge);
    }

    @Override
    public Map<String, Supplier<State>> getCommands() {
        return new HashMap<>();
    }
}
