package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        BufferedReader in = new BufferedReader(new FileReader(file1));
        BufferedWriter out = new BufferedWriter(new FileWriter(file2));

        while(in.ready()){
            String str = in.readLine();
            str = str.replaceAll("\\p{Punct}", "");
            out.write(str);
            out.newLine();
        }

        in.close();
        out.close();
/*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        while (reader.ready()) {
            String s = reader.readLine();
            s = s.replaceAll("\\p{Punct}", "");
            writer.write(s);
        }
        reader.close();
        writer.close();
*/
    }
}
