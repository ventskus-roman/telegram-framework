package com.romanventskus.bot.framework;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.questions.Question;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public abstract class State<OUTPUT_CHANNEL extends OutputChannel, T extends User> {

    protected Question currentQuestion;

    @Setter
    public OUTPUT_CHANNEL outputChannel;

    @Getter
    private T user;

    abstract protected State handle(Message message, T user);

    public State process(Message message, T user) {
        this.user = user;
        Supplier<State> command = getCommands().get(message.getText());
        boolean isCommand = command != null;
        if (isCommand) {
            State newState = command.get();
            return newState;
        }
        if (currentQuestion == null) {
            return handle(message, user);
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
            return handle(message, user);
        } else {
            outputChannel.send(new OutputMessage(getUser(), currentQuestion.getInvalidMessage(), null));
            return this;
        }
    }

    public Set<Question> getQuestions() {
        return Sets.newHashSet();
    };

    public Map<String, Supplier<State>> getCommands() {
        return Maps.newHashMap();
    };

    protected void send(String message, ReplyKeyboard keyboard) {
        OutputMessage outputMessage = new OutputMessage(getUser(), message, keyboard);
        outputChannel.send(outputMessage);
    }

    protected void send(String message) {
        send(message, null);
    }

    protected void ask(Question question) {
        currentQuestion = question;
        outputChannel.send(new OutputMessage(getUser(), question.getText(), question.getReplyKeyboard()));
    }
}
