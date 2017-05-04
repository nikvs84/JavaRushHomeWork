package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] ar = new  int[256];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        int j = 0;
        while (stream.available() > 0) {
            j = stream.read();
            ar[j]++;
        }
        int min = ar[j];
        for (int i : ar) {
            if (i > 0 && i < min)
                min = i;
        }
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == min)
                System.out.print(i + " ");
        }
    }
}
