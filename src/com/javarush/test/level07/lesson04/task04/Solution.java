package com.javarush.test.level07.lesson04.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Массив из чисел в обратном порядке
1. Создать массив на 10 чисел.
2. Ввести с клавиатуры 10 чисел и записать их в массив.
3. Расположить элементы массива в обратном порядке.
4. Вывести результат на экран, каждое значение выводить с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ai = new int[10];
        int m;
//        ai[0] = Integer.parseInt(reader.readLine());
//        for (int i = 0; i < ai.length; i++)
//        {
//            ai[i] = Integer.parseInt(reader.readLine());
//            for (int j = 0; j < i; j++)
//            {
//                if (ai[i] > ai[j]){
//                    m = ai[j];
//                    ai[j] = ai[i];
//                    ai[i] = m;
//                }
//            }
//        }
        for (int i = 0; i < 10; i++)
        {
            ai[i] = Integer.parseInt(reader.readLine());
        }
        m = ai.length - 1;
        for (int i = m; i > -1; i--)
        {
            System.out.println(ai[i]);
        }
    }
}
