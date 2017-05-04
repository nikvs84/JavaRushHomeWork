package com.javarush.test.level04.lesson01.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Реализовать метод addPrice
Реализовать метод addPrice(int applesPrice), чтобы при его вызове суммарная стоимость яблок увеличивалось на переданное значение.
За суммарную стоимость яблок отвечает переменная public static int applesPrice.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        Apple apple = new Apple();
        Apple.addPrice(50);
        Apple apple2 = new Apple();
        Apple.addPrice(100);
        System.out.println("Apples price is " + Apple.applesPrice);
    }

    public static class Apple{
        public static int applesPrice = 0;

        public static void addPrice(int applesPrice) throws IOException
        {
            Apple.applesPrice=applesPrice+Apple.applesPrice;
            //напишите тут ваш код
        }
    }
}
