package com.javarush.test.level19.lesson10.home06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Замена чисел
1. В статическом блоке инициализировать словарь map парами [число-слово] от 0 до 12 включительно
Например, 0 - "ноль", 1 - "один", 2 - "два"
2. Считать с консоли имя файла
3. Заменить все числа на слова используя словарь map
4. Результат вывести на экран
5. Закрыть потоки. Не использовать try-with-resources

Пример данных:
Это стоит 1 бакс, а вот это - 12 .
Переменная имеет имя file1.
110 - это число.

Пример вывода:
Это стоит один бакс, а вот это - двенадцать .
Переменная имеет имя file1.
110 - это число.
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));

        while (file.ready()){
            String[] words = file.readLine().replace("\n", "").split(" ");

            for (int i = 0; i < words.length; i++){
                String currentStr = words[i];
                String replacedStr = words[i].replace(".", " ").trim();

                Pattern pattern = Pattern.compile("\\b\\d+\\b");
                Matcher matcher = pattern.matcher(replacedStr);

                if (matcher.find()){
                    try{
                        int num = Integer.parseInt(replacedStr);
                        for (Map.Entry<Integer, String> pair: map.entrySet()){
                            if (pair.getKey().equals(num)){
                                currentStr = currentStr.replace(replacedStr, pair.getValue());
                                break;
                            }
                        }
                    }catch (NumberFormatException e){}
                }
                if (i == words.length-1){
                    System.out.print(currentStr);
                }else
                    System.out.print(currentStr + " ");
            }
            System.out.println();
        }

        reader.close();
        file.close();
/*        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
//        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String string = reader.readLine();
            String[] strings = string.split(" ");
            for (int i = 0; i < strings.length; i++) {
                String st = strings[i];
                String s = strings[i].trim();
                if (s.matches("\\d+")) {
                    int n = Integer.parseInt(s);
                    if (n < 13) {
                        String ss = map.get(n);
                        st = st.replace(s, ss);
                        string = string.replaceFirst(strings[i], st);
                    }
                }
            }
//            list.add(string);
            System.out.println(string);
        }
        reader.close();
*//*
        if (!list.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//            BufferedWriter writer = new BufferedWriter(new FileWriter("d:\\i1.txt"));
            for (int i = 0; i < list.size() - 1; i++) {
                writer.write(list.get(i));
                writer.newLine();
            }
            writer.write(list.get(list.size() - 1));
            writer.close();
        }
*/
    }
}
