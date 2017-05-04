package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        int[] num = new int[5];
        for (int i = 0; i < 5; i++)
        {
            num[i] = Integer.parseInt(reader.readLine());
        }

        int d;

        for (int i = 0; i < 4; i++)
        {
            for (int j = i+1; j < 5; j++)
            {
                if (num[i] > num[j])
                {
                    d = num[i];
                    num[i] = num[j];
                    num[j] = d;
                }
            }

            System.out.println(num[i]);
        }

        System.out.println(num[4]);

        //напишите тут ваш код
    }
}
