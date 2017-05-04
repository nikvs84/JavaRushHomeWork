package com.javarush.test.level04.lesson07.task01;

/* Строка - описание
Ввести с клавиатуры целое число. Вывести на экран его строку-описание следующего вида:
«отрицательное четное число» - если число отрицательное и четное,
«отрицательное нечетное число» - если число отрицательное и нечетное,
«нулевое число» - если число равно 0,
«положительное четное число» - если число положительное и четное,
«положительное нечетное число» - если число положительное и нечетное.
Пример для числа 100:
положительное четное число
Пример для числа -51:
отрицательное нечетное число
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String s=reader.readLine();
        int i=Integer.parseInt(s);
        if (i==0)
        {
            s = "нулевое число";
            System.out.println(s);
        }
        else
        {
            if (i > 0)
                s = "положительное";
            else if (i < 0)
                s = "отрицательное";
            if (i % 2 == 0)
              s = s + " четное число";
            else
                s = s + " нечетное число";
            System.out.println(s);
        }
        //напишите тут ваш кtод

    }
}
