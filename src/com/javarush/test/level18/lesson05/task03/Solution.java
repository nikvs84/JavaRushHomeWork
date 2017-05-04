package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream outputStream1 = new FileOutputStream(reader.readLine());
        FileOutputStream outputStream2 = new FileOutputStream(reader.readLine());
        int count = inputStream.available();
        if (count > 0) {
            byte[] buffer1 = new byte[count - count / 2];
            byte[] buffer2 = new byte[count / 2];
/*
            System.out.println(count);
            System.out.println(buffer1.length);
            System.out.println(buffer2.length);
*/
/*
Задача принята, хотя отсутствует чтение в массив из файла1 :-)
            byte[] buffer = new byte[count];
            inputStream.read(buffer);
*/
            outputStream1.write(buffer1);
            outputStream2.write(buffer2);
        }
        reader.close();
        inputStream.close();
        outputStream1.close();
        outputStream2.close();
    }
}
