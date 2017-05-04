package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 14.10.2016.
 */
class DepositCommand implements Command {

    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator currencyManipulator =  CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        String[] denomin = ConsoleHelper.getValidTwoDigits(currencyManipulator.getCurrencyCode());
        try {
            int nominal = Integer.parseInt(denomin[0]);
            int count = Integer.parseInt(denomin[1]);
            currencyManipulator.addAmount(nominal, count);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), nominal, count));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
