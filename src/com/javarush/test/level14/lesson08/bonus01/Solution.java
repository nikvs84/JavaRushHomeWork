package com.javarush.test.level14.lesson08.bonus01;

import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.InvalidOpenTypeException;
import java.awt.*;
import java.io.IOException;
import java.io.NotActiveException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        exceptions.add(new NotBoundException());
        exceptions.add(new IOException());
        exceptions.add(new NullPointerException());
        exceptions.add(new InvalidKeyException());
        exceptions.add(new NotActiveException());
        exceptions.add(new IndexOutOfBoundsException());
        exceptions.add(new EmptyStackException());
        exceptions.add(new InvalidOpenTypeException());
        exceptions.add(new HeadlessException());
    }
}
