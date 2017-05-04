package com.javarush.test.level19.lesson10.home08;

/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть потоки. Не использовать try-with-resources

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileIn = new BufferedReader(new FileReader(reader.readLine()));

        while (fileIn.ready()){
            String line = fileIn.readLine();
            System.out.println(new StringBuffer(line).reverse().toString());
        }

        reader.close();
        fileIn.close();
/*        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) {
            StringBuilder stringBuilder = new StringBuilder(reader.readLine());
            System.out.println(stringBuilder.reverse().toString());
        }
        reader.close();*/
    }
}
