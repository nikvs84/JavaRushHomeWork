package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] ar = new  int[256];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        while (stream.available() > 0) {
            ar[stream.read()]++;
        }
        int max = ar[0];
        for (int i : ar) {
            if (i > max)
                max = i;
        }
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == max)
                System.out.print(i + " ");
        }
    }
}
