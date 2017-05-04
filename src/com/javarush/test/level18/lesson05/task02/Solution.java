package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        byte[] buffer = new byte[stream.available()];
        if (stream.available() > 0) {
            stream.read(buffer);
        }
        int count = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == 44)
                count++;
        }
        System.out.println(count);
        reader.close();
        stream.close();
    }
}
