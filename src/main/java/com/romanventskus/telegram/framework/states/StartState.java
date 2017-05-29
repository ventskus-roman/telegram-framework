package com.romanventskus.telegram.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.telegram.framework.Message;
import com.romanventskus.telegram.framework.State;
import com.romanventskus.telegram.framework.StateProvider;
import com.romanventskus.telegram.framework.channel.OutputChannel;
import com.romanventskus.telegram.framework.questions.Question;

import java.util.Set;

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
            ask(whatIsYourName);
            return this;
        }
        if (!whatIsYourAge.isAnswered()) {
            ask(whatIsYourAge);
            return this;
        }
        outputChannel.send("Your name is " + whatIsYourName.getAnswer());
        outputChannel.send("Your age is " + whatIsYourAge.getAnswer());
        return new SecondState(outputChannel, stateProvider);
    }

    @Override
    public Set<Question> getQuestions() {
        return Sets.newHashSet(whatIsYourName, whatIsYourAge);
    }
}
