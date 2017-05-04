package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileReader fileReader = new FileReader(reader.readLine());
        reader = new BufferedReader(fileReader);
        ArrayList<Integer> list = new ArrayList<>();
        String s = reader.readLine();
        while (!s.isEmpty()) {
            int i = Integer.parseInt(s);
            if (i % 2 == 0)
            list.add(i);
            s = reader.readLine();
            if (s == null) break;
        }
        if (list.size() > 0) {
//            for (int i = 0; i < list.size();) {
//                if (list.get(i) % 2 != 0) {
//                    list.remove(i);
//                }
//                else i++;
//            }
        int t;
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i) > list.get(j)) {
                        t = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, t);
                    }
                }
                System.out.println(list.get(i));
            }
            System.out.println(list.get(list.size() - 1));
        }
    }
}
