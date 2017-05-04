package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 16.10.2016.
 */
public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cNumb = "";
        String cPin = "";
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cNumb = ConsoleHelper.readString();
            cPin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(cNumb)) {
                if (validCreditCards.getString(cNumb).equals(cPin)) {
                    ConsoleHelper.writeMessage(res.getString(String.format(res.getString("success.format"), cNumb)));
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString(String.format(res.getString("not.verified.format"), cNumb)));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
            else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cNumb));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }
        }
    }

    public static void main(String[] args) throws InterruptOperationException {
        LoginCommand lc = new LoginCommand();
        lc.execute();
    }
}
