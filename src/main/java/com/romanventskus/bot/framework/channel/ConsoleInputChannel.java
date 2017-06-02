package com.romanventskus.bot.framework.channel;

import com.romanventskus.bot.framework.User;
import com.romanventskus.bot.framework.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * @author r.ventskus
 */
public class ConsoleInputChannel implements InputChannel {

    User user = new User(){};

    @Override
    public void listen(Consumer<Message> process) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        while (line.equalsIgnoreCase("quit") == false) {
            try {
                line = in.readLine();
                process.accept(new Message(user, line));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
