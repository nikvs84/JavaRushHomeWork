package com.javarush.test.level07.lesson12.home02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Переставить M первых строк в конец списка
Ввести с клавиатуры 2 числа N  и M.
Ввести N строк и заполнить ими список.
Переставить M первых строк в конец списка.
Вывести список на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();

        //напишите тут ваш код
        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());
        if (N > M)
        {
            for (int i = 0; i < N; i++)
            {
                list.add(reader.readLine());
            }
            String s="";
            int n = list.size()-1;
            for (int i = 0; i < M; i++)
            {
                s = list.get(0);
                list.remove(0);
                list.add(n, s);
            }

            for (int i = 0; i < list.size(); i++)
            {
                System.out.println(list.get(i));
            }
        }
    }
}
