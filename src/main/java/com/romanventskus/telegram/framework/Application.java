package com.romanventskus.telegram.framework;

import com.romanventskus.telegram.framework.channel.ConsoleInputChannel;
import com.romanventskus.telegram.framework.channel.OutputChannel;

public class Application {

    public static void main(String[] args) {
        OutputChannel outputChannel = message -> System.out.println(message);
        Framework framework = new Framework(new ConsoleInputChannel(),
                outputChannel,
                new StateProviderImpl(outputChannel));
        framework.start();
    }
}
