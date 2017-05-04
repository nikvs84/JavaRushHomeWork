package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 14.10.2016.
 */
class WithdrawCommand implements Command {

    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Enter currency code");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        int summ = 0;
        summ = Integer.parseInt(ConsoleHelper.readString());
        while (true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            if (summ <= 0) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!currencyManipulator.isAmountAvailable(summ)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } else
                break;

            summ = Integer.parseInt(ConsoleHelper.readString());

        }

        try {
            currencyManipulator.withdrawAmount(summ);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), summ, currencyCode));
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }

    }
}
