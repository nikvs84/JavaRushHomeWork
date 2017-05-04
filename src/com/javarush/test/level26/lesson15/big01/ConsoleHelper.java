package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Admin on 14.10.2016.
 */
public class ConsoleHelper {

    private static ResourceBundle res = PropertyResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        String result = "";
        try {
            result =  reader.readLine();
        } catch (IOException ignore) {
        }
        if ("exit".equalsIgnoreCase(result))
            throw new InterruptOperationException();
        return result;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result= "";
        writeMessage(res.getString("choose.currency.code"));

        result = readString().toUpperCase();

        while (result.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            result = readString().toUpperCase();
        }
        return result;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result = new String[2];
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        result = readString().split(" ");
        int nom = Integer.parseInt(result[0]);
        int countM = Integer.parseInt(result[1]);
        while ((nom < 1) || (countM < 1)) {
            writeMessage("invalid.data");
            result = readString().split(" ");
            try {
                nom = Integer.parseInt(result[0]);
                countM = Integer.parseInt(result[1]);
            } catch (NumberFormatException e) {
                writeMessage("invalid.data");
            }
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation operation = null;
        writeMessage(res.getString("choose.operation"));
        int i = 0;
        while (operation == null) {
            i = Integer.parseInt(readString());
            try {
                operation = Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                writeMessage("invalid.data");
            }
        }
        return operation;
    }
}
