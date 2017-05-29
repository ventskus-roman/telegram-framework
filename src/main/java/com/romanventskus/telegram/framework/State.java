package com.romanventskus.telegram.framework;

import com.romanventskus.telegram.framework.channel.OutputChannel;
import com.romanventskus.telegram.framework.questions.Question;

import java.util.Set;

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
        if (currentQuestion == null) {
            return handle(message);
        } else {
            boolean valid = currentQuestion.getValidator().test(message.getMessage());
            if (valid) {
                currentQuestion.setAnswer(message.getMessage());
                currentQuestion = null;
                return handle(message);
            } else {
                outputChannel.send(currentQuestion.getInvalidMessage());
                return this;
            }
        }
    }

    abstract public Set<Question> getQuestions();

    protected void ask(Question question) {
        currentQuestion = question;
        outputChannel.send(question.getText());
    }
}
