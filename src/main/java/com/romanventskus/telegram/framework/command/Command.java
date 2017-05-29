package com.romanventskus.telegram.framework.command;

import com.romanventskus.telegram.framework.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

/**
 * Created by romanventskus on 29.05.17.
 */
@Getter
@Setter
@AllArgsConstructor
public class Command {

    private String text;
    private Callable<State> callable;

}
