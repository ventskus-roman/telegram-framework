package com.romanventskus.bot.framework.questions;

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
    private Function<String, T> convertor;
    @Setter
    private T answer;

    public Question(String name, String text, String invalidMessage, Predicate<String> validator) {
        this.name = name;
        this.text = text;
        this.invalidMessage = invalidMessage;
        this.validator = validator;
    }

    public Question(String name, String text, String invalidMessage, Predicate<String> validator, Function<String, T> convertor) {
        this(name, text, invalidMessage, validator);
        this.convertor = convertor;
    }

    public boolean isAnswered() {
        return answer != null;
    }

    public void setAnswer(String answer) {
        if (answer == null) {
            this.answer = null;
        } else {
            if (convertor == null) {
                this.answer = (T) answer;
            } else {
                this.answer = convertor.apply(answer);
            }
        }
    }
}
