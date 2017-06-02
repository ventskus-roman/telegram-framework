package com.romanventskus.bot.framework.telegram;

import com.romanventskus.bot.framework.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by romanventskus on 29.05.17.
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class TelegramUser implements User {

    Long id;
}
