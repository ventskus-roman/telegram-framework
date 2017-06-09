package com.romanventskus.bot.framework.states;

import com.google.common.collect.Sets;

import com.romanventskus.bot.framework.Message;
import com.romanventskus.bot.framework.State;
import com.romanventskus.bot.framework.User;
import com.romanventskus.bot.framework.questions.Question;

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

    @Override
    public State handle(Message message, User user) {
        if (!whatIsYourName.isAnswered()) {
            ask(whatIsYourName);
            return this;
        }
        if (!whatIsYourAge.isAnswered()) {
            ask(whatIsYourAge);
            return this;
        }
        send("Your name is " + whatIsYourName.getAnswer());
        send("Your age is " + whatIsYourAge.getAnswer());
        return new SecondState();
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
