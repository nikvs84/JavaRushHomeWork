package com.javarush.test.level32.lesson02.task01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) {
        if (args.length < 3) {
            args = new String[3];
            args[0] = "f:/javarush/1.txt";
            args[1] = "10";
            args[2] = "ttt абв";
        }
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            long fileSize = raf.length();
            long number = Long.parseLong(args[1]);
            if (number > fileSize) {
                raf.seek(fileSize);
            } else {
                raf.seek(number);
            }
            raf.writeBytes(args[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
