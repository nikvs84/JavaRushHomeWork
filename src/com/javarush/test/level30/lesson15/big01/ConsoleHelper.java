package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Admin on 31.10.2016.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String result = "";
        boolean isReaded = false;
        do {
            try {
                result = reader.readLine();
                isReaded = true;
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                isReaded = false;
            }
        } while (!isReaded);
        return result;
    }

    public static int readInt() {
        int result = 0;
        boolean isReaded = false;
        do {
            try {
                result = Integer.parseInt(readString());
                isReaded = true;
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                isReaded = false;
            }
        } while (!isReaded);
        return result;
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Hello");
        ConsoleHelper.writeMessage(ConsoleHelper.readInt() + "");
    }
}
