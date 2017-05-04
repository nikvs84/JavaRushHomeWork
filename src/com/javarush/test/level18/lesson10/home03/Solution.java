package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        FileInputStream inputStream2 = new FileInputStream(fileName2);
        FileInputStream inputStream3 = new FileInputStream(fileName3);
        FileOutputStream outputStream1 = new FileOutputStream(fileName1);
        while (inputStream2.available() > 0) {
            outputStream1.write(inputStream2.read());
        }
        byte[] buffer = new byte[inputStream3.available()];
        if (inputStream3.read(buffer) > 0) {
            outputStream1.write(buffer);
        }
        reader.close();
        outputStream1.close();
        inputStream2.close();
        inputStream3.close();
    }
}
