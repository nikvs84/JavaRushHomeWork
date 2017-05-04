package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        FileInputStream inputStream1 = new FileInputStream(filename1);
        FileInputStream inputStream2 = new FileInputStream(filename2);
        byte[] buffer1 = new byte[inputStream1.available()];
        byte[] buffer2 = new byte[inputStream2.available()];
        inputStream1.read(buffer1);
        inputStream2.read(buffer2);
        inputStream1.close();
        inputStream2.close();
        byte[] buffer = new  byte[buffer1.length + buffer2.length];
        System.arraycopy(buffer2, 0, buffer, 0, buffer2.length);
        System.arraycopy(buffer1, 0, buffer, buffer2.length, buffer1.length);
        FileOutputStream outputStream1 = new FileOutputStream(filename1);
        if (buffer.length > 0) {
            outputStream1.write(buffer);
        }
        outputStream1.close();
    }
}
