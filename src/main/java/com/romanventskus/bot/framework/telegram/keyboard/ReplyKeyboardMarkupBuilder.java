package com.romanventskus.bot.framework.telegram.keyboard;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Ventskus on 26.04.2016.
 */
public class ReplyKeyboardMarkupBuilder {

    private ReplyKeyboardMarkup markup;
    private List<KeyboardRow> keyboard;

    public ReplyKeyboardMarkupBuilder() {
        markup = new ReplyKeyboardMarkup();
        keyboard = new ArrayList<>();
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
    }

    public ReplyKeyboardMarkupBuilder addRow(String... buttons) {
        KeyboardRow row = new KeyboardRow();
        if (buttons != null) {
            for (String button : buttons) {
                row.add(button);
            }
        }
        keyboard.add(row);
        return this;
    }

    public ReplyKeyboardMarkupBuilder oneTimeKeyboad() {
        markup.setOneTimeKeyboad(true);
        return this;
    }

    public ReplyKeyboardMarkupBuilder selective() {
        markup.setSelective(true);
        return this;
    }

    public ReplyKeyboardMarkupBuilder resizeKeyboard() {
        markup.setResizeKeyboard(true);
        return this;
    }

    public ReplyKeyboardMarkup build() {
        return markup;
    }
}
