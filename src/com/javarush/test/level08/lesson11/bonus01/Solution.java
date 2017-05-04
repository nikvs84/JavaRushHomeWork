package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
Используйте коллекции.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();
        HashMap<String, Integer> monthMap = new HashMap<>();
        monthMap.put("JANUARY", 0);
        monthMap.put("FEBRUARY", 1);
        monthMap.put("MARCH", 2);
        monthMap.put("APRIL", 3);
        monthMap.put("MAY", 4);
        monthMap.put("JUNE", 5);
        monthMap.put("JULY", 6);
        monthMap.put("AUGUST", 7);
        monthMap.put("SEPTEMBER", 8);
        monthMap.put("OCTOBER", 9);
        monthMap.put("NOVEMBER", 10);
        monthMap.put("DECEMBER", 11);
        if (monthMap.containsKey(month.toUpperCase()))
        System.out.println(month + " is " + (monthMap.get(month.toUpperCase()) + 1) + " month");
    }

}
