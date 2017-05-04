package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream stream = new FileInputStream(args[0]);
        int vol = stream.available();
        int count = 0;
        if (args[0].length() > 0) {
            byte[] chars = new byte[vol];
            stream.read(chars);
            for (int i = 0; i < chars.length; i++) {
                if ((chars[i] >= ((byte) 'a') && chars[i] <= ((byte) 'z')) || (chars[i] >= ((byte) 'A') && chars[i] <= ((byte) 'Z'))) {
                    count++;
                }
            }
            System.out.println(count);
            stream.close();
        }
    }
}
