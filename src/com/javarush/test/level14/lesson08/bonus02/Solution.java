package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        if (a > 0 && b > 0) {
            System.out.println(nod(a, b));
        }
    }

    public static int nod(int a, int b) {
        int nod = 1;
        int min = min(a, b) +1;
        for (int i = 1; i < min; i++) {
            if (a % i == 0)
                if (b % i ==0)
                    nod = i;
        }
        return nod;
    }

    public static int min(int a, int b) {
        if (a < b) return a;
        else return b;
    }
}
