package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        FileInputStream inputStream = new FileInputStream(filename);
        int fileSize = inputStream.available();
        int[] bytes = new int[256];
        while (inputStream.available() > 0) {
            byte b = (byte) inputStream.read();
            bytes[b]++;
        }

        TreeMap<Byte, Integer> map = new TreeMap<>();
        for (int i = 0; i < 256; i++) {
            if (bytes[i] > 0)
            map.put((byte) i, bytes[i]);
        }

        for (Map.Entry<Byte, Integer> pair : map.entrySet()) {
            System.out.println((char) pair.getKey().byteValue() + " " + pair.getValue());
        }
        inputStream.close();
    }
}
