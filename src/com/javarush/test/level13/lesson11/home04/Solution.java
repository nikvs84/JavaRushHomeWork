package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String fileName = reader.readLine();
//            FileOutputStream outputStream = new FileOutputStream(fileName);
//            String s;
//            ArrayList<char[]> list = new ArrayList<>();
//        while (true){
//            s = reader.readLine();
//            if ("exit".equals(s)) break;
//            list.add(s.toCharArray());
//        }
//        for (int i = 0; i < list.size(); i++) {
//            for (char ch: list.get(i)) {
//                outputStream.write((int)ch);
//            }
//            if (i == list.size() - 1) break;
//            outputStream.write(13);
//            outputStream.write(10);
//        }
//        outputStream.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileWriter fileWriter = new FileWriter(fileName);
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            list.add(s);
            if ("exit".equals(s))
                break;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            fileWriter.write(list.get(i));
            fileWriter.write(13);
            fileWriter.write(10);
        }
        fileWriter.write(list.get(list.size() - 1));
        fileWriter.close();
    }
}