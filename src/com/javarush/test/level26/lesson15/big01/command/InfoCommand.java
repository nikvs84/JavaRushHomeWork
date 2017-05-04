package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 14.10.2016.
 */
class InfoCommand implements Command {
    private ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean money = false;
        for (CurrencyManipulator c : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (c.hasMoney()) {
                if (c.getTotalAmount() > 0) {
                    System.out.println(c.getCurrencyCode() + " - " + c.getTotalAmount());
                    money = true;
                }
            }
        }
        if (!money) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
