package com.romanventskus.telegram.framework.questions;

import java.util.function.Predicate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author r.ventskus
 */
@Getter
public class Question<T> {

    private String name;
    private String text;
    private String invalidMessage;
    private Predicate<String> validator;
    @Setter
    private T answer;

    public Question(String name, String text, String invalidMessage, Predicate<String> validator) {
        this.name = name;
        this.text = text;
        this.invalidMessage = invalidMessage;
        this.validator = validator;
    }

    public boolean isAnswered() {
        return answer != null;
    }
}
