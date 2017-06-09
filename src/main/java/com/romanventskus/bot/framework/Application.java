package com.romanventskus.bot.framework;

import com.romanventskus.bot.framework.channel.OutputChannel;
import com.romanventskus.bot.framework.channel.ConsoleInputChannel;

public class Application {

    public static void main(String[] args) {
        OutputChannel outputChannel = (message) -> System.out.println(message.getText());
        Framework framework = new Framework(new ConsoleInputChannel(),
                outputChannel,
                new StateProviderImpl(), new UserStore() {
            @Override
            public User get(Sender sender) {
                return null;
            }
        });
        framework.start();
    }
}
