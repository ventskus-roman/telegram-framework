package com.romanventskus.bot.framework;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.questions.Question;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public abstract class State {

    protected Question currentQuestion;

    protected OutputChannel outputChannel;
    protected StateProvider stateProvider;

    public State(OutputChannel outputChannel, StateProvider stateProvider) {
        this.outputChannel = outputChannel;
        this.stateProvider = stateProvider;
    }

    abstract protected State handle(Message message);

    public State process(Message message) {
        Supplier<State> command = getCommands().get(message.getText());
        boolean isCommand = command != null;
        if (isCommand) {
            State newState = command.get();
            return newState;
        }
        if (currentQuestion == null) {
            return handle(message);
        } else {
            return processQuestion(message);
        }
    }

    public void clear() {
        getQuestions().forEach(question -> question.setAnswer(null));
    }

    private State processQuestion(Message message) {
        boolean valid = currentQuestion.getValidator().test(message.getText());
        if (valid) {
            currentQuestion.setAnswer(message.getText());
            currentQuestion = null;
            return handle(message);
        } else {
            outputChannel.send(currentQuestion.getInvalidMessage(), message.getUser());
            return this;
        }
    }

    abstract public Set<Question> getQuestions();

    abstract public Map<String, Supplier<State>> getCommands();

    protected void ask(Question question, User user) {
        currentQuestion = question;
        outputChannel.send(question.getText(), user);
    }
}
