package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new TreeSet<>();
        String fileName = reader.readLine();
        while (!"end".equals(fileName)) {
            set.add(fileName);
            fileName = reader.readLine();
        }
        Iterator<String> iterator = set.iterator();
        if (iterator.hasNext())
        fileName = iterator.next();
        String filename2 = fileName.substring(0, fileName.lastIndexOf('.'));
        FileInputStream inputstream = null;
        FileOutputStream outputstream = null;
        for (String fName : set) {
            inputstream = new FileInputStream(fName);
            int filesize = inputstream.available();
            byte[] bytes = new byte[filesize];
            inputstream.read(bytes);
            outputstream = new FileOutputStream(filename2, true);
            outputstream.write(bytes);
        }
        reader.close();
        inputstream.close();
        outputstream.close();
    }
}
