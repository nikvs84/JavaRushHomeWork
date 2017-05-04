package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        double d = 0;
        int i = 0;
        String s = reader.readLine();
        while (!"сумма".equals(s)){
            i += Integer.parseInt(s);
//            d += Double.parseDouble(s);
            s = reader.readLine();
        }
        System.out.println(i);
        //напишите тут ваш код
    }
}
