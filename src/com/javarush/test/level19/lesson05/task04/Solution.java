package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
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
            str = str.replaceAll("\\.", "!");
            out.write(str);
            out.newLine();
        }

        in.close();
        out.close();
/*        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String s = reader.readLine();
            s = s.replaceAll("\\.", "\\!");
            list.add(s);
        }
        for (int i = 0; i < list.size() - 1; i++) {
            writer.write(list.get(i));
            writer.newLine();
        }
        writer.write(list.get(list.size() - 1));
        reader.close();
        writer.close();*/
    }
}
