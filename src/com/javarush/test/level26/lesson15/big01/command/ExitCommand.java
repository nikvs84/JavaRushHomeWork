package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 14.10.2016.
 */
class ExitCommand implements Command {
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String ans = ConsoleHelper.readString();
        while (true) {
            if (res.getString("yes").equalsIgnoreCase(ans)) {
                ConsoleHelper.writeMessage(res.getString("thank.message"));
                break;
            }
            if ("n".equalsIgnoreCase(ans)) {

            }
        }
    }
}
