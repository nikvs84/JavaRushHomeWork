package com.javarush.test.level36.lesson08.task01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        File file = new File(args[0]);
        //читаем строку
        String text = reader(file);

        //операции по удалению лишнего
        //text = textCutter(text);

        //добавляем посимвольно в arraylist
        List<String> stringList = Arrays.asList(text.toLowerCase().split(""));
        //создаем set
        TreeSet<String> treeSet = new TreeSet<>();

        //добавляем символы в set
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            if((s.matches("\\w")) && (s.matches("[^0-9]")))
                treeSet.add(stringList.get(i));
        }

        //выводим
        Iterator iterator = treeSet.iterator();
        if (treeSet.size() >= 5) {

            int count = 0;
            while (iterator.hasNext() && count < 5) {
                System.out.print(iterator.next());
                count ++;
            }

        } else {
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
        }


    }

    public static String reader(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String res = "";
        int b = raf.read();
        while(b != -1){
            res = res + (char)b;
            b = raf.read();
        }
        raf.close();
        return res;
    }
}

/*
* Решение не принимается*/
/*
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            args = new String[1];
            args[0] = "f:/javarush/b.txt";
        }
        List<String> strings = Files.readAllLines(Paths.get(args[0]));

        TreeSet<Character> characters = new TreeSet<Character>();
        for (String string : strings) {
            for (Character c : string.toCharArray()) {
                if (Character.isLetter(c)) {
                characters.add(Character.toLowerCase(c));
                }
            }
        }
        int i = 5;
        for (Character c : characters) {
            if (i == 0)
                break;
            System.out.print(c);
            i--;
        }
    }
}
*/