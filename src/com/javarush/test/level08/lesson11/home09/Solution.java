package com.javarush.test.level08.lesson11.home09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println(s + " = " + isDateOdd(s));
    }

    public static boolean isDateOdd(String date)
    {
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
        int i=0, day, year;
        String mon = "";
            String sd = "", sy = "";
            while (date.charAt(i)!=' '){
                mon = mon + date.charAt(i);
                i++;
            }
            while (date.charAt(i) == ' ') {
                i++;
            }
            while (date.charAt(i)!=' '){
                sd = sd + date.charAt(i);
                i++;
            }
            day = Integer.parseInt(sd);
            while (date.charAt(i) == ' ') {
                i++;
            }
            while (i < date.length()){
                sy = sy + date.charAt(i);
                i++;
            }
            year = Integer.parseInt(sy);
        Date start = new Date();
        start.setSeconds(0);
        start.setMinutes(0);
        start.setHours(0);
        start.setDate(1);
        start.setMonth(0);
        start.setYear(year);
        Date dEnd = new Date();
        dEnd.setSeconds(0);
        dEnd.setMinutes(0);
        dEnd.setHours(0);
        dEnd.setDate(day);
        dEnd.setMonth(monthMap.get(mon));
        dEnd.setYear(year);
//        System.out.println("start "+start.getMonth() + " " + start.getDate() + " " + start.getYear());
//        System.out.println("end "+dEnd.getMonth() + " " + dEnd.getDate() + " " + dEnd.getYear());
        long dist = dEnd.getTime() - start.getTime();
//        System.out.println("dist "+dist);
        long msDay = 1000*60*60*24;
//        System.out.println("msDay "+msDay);
        int dayCount = (int) (dist/msDay);
//        System.out.println(dayCount);
//        System.out.println("dayCount "+dayCount);
        return (dayCount%2 == 0);
    }
}
