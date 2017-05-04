package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileReader inFile = new FileReader(reader.readLine());
        int count = 0;
        String str = "";

        while (inFile.ready()){
            char symbol = (char) inFile.read();
            str += String.valueOf(symbol).toLowerCase();
        }

        str = str.replaceAll("\\p{Punct}", " ");
        str = str.replaceAll("\n", " ");
        //System.out.println(str);

        String[] mas = str.split(" ");
        for(String s: mas){
            s = s.trim();
            if (s.equals("world"))
                count++;
        }

        System.out.println(count);
        reader.close();
        inFile.close();
/*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        int count = 0;
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append(reader.readLine() + " ");
        }
        reader.close();
        String content = sb.toString().trim();
        content = content.replaceAll("\\p{Punct}", " ");
        String[] as = content.split(" ");
        for (String s : as) {
            if ("world".equals(s))
                count++;
        }
        System.out.println(count);
*/
    }
}
