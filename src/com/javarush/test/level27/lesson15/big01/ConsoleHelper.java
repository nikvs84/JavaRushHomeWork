package com.javarush.test.level27.lesson15.big01;


import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.10.2016.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() throws IOException {
        String result = null;
            result = reader.readLine();
        return result;
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        String str;
        writeMessage("Enter dish...(" + Dish.allDishesToString() + ")");
        while (true) {
            str = readString();
            if ("exit".equals(str)) {
                break;
            }

            try {
                dishes.add(Dish.valueOf(str));
            }
            catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(str + " is not detected");
            }
        }
        return dishes;
    }

}
