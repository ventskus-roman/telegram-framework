package com.romanventskus.bot.framework;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.channel.ConsoleInputChannel;

public class Application {

    public static void main(String[] args) {
        OutputChannel outputChannel = (message, user) -> System.out.println(message);
        Framework framework = new Framework(new ConsoleInputChannel(),
                outputChannel,
                new StateProviderImpl(outputChannel));
        framework.start();
    }
}
