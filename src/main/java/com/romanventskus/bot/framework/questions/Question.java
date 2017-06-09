package com.romanventskus.bot.framework.questions;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.util.function.Function;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author r.ventskus
 */
@Getter
public class Question<T> {

    private String name;
    @Setter
    private String text;
    private String invalidMessage;
    private Predicate<String> validator;
    private Function<String, T> converter;
    @Setter
    private T answer;
    private ReplyKeyboard replyKeyboard;

    public Question(String name, String text, String invalidMessage, Predicate<String> validator) {
        this.name = name;
        this.text = text;
        this.invalidMessage = invalidMessage;
        this.validator = validator;
    }

    public Question(String name, String text, String invalidMessage, Predicate<String> validator, ReplyKeyboard replyKeyboard) {
        this(name, text, invalidMessage, validator);
        this.replyKeyboard = replyKeyboard;
    }


    public Question(String name, String text, String invalidMessage, Predicate<String> validator, Function<String, T> converter) {
        this(name, text, invalidMessage, validator);
        this.converter = converter;
    }

    public boolean isAnswered() {
        return answer != null;
    }

    public void setAnswer(String answer) {
        if (answer == null) {
            this.answer = null;
        } else {
            if (converter == null) {
                this.answer = (T) answer;
            } else {
                this.answer = converter.apply(answer);
            }
        }
    }
}
