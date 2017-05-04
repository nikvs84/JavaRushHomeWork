package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[1];
        String fileOutputName = args[2];
        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream = new FileOutputStream(fileOutputName);
        byte[] buffer = new byte[inputStream.available()];
        if (inputStream.read(buffer) > 0) {
            if ("-e".equals(args[0])) {
                outputStream.write(encrypte(buffer));
            } else if ("-d".equals(args[0])) {
                outputStream.write(decrypte(buffer));
            }
        }
        inputStream.close();
        outputStream.close();
    }

    public static byte[] encrypte(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]++;
        }
        return bytes;
    }

    public static byte[] decrypte(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]--;
        }
        return bytes;
    }
}
