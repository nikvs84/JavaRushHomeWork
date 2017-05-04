package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s : list) {
            System.out.println(s);
        }
        if ("-d".equals(args[0])) {
            removeItem(list, getId(args[1]));
        } else
            if ("-u".equals(args[0])) {
            updateItem(list, getId(args[1]), updateString(args));
        }

        BufferedWriter writer = new BufferedWriter(new PrintWriter(fileName));
        writeList(writer, list);
        writer.close();
    }
    public static void writeList(BufferedWriter writer, ArrayList<String> list) throws IOException {
        for (int i = 0; i < list.size() - 1; i++) {
            writer.write(list.get(i));
            writer.newLine();
        }
        writer.write(list.get(list.size() - 1));
        writer.flush();
    }

    public static int getId(String s) {
        return Integer.parseInt(s.trim());
    }

    public static void removeItem(ArrayList<String> list, int id) {
        String s;
        for (int i = 0; i < list.size(); i++) {
            s = list.get(i).substring(0, 8);
            if (id == Integer.parseInt(s.trim()))
                list.remove(i);
        }
    }

    public static void updateItem(ArrayList<String> list, int id, String updString) {
        String s;
        for (int i = 0; i < list.size(); i++) {
            s = list.get(i).substring(0, 8).trim();
            if (id == Integer.parseInt(s))
                list.set(i, updString);
        }
    }

    public static String updateString(String[] args) {
        return appendSpace(args[1], 8) + appendSpace(args[2], 30) + appendSpace(args[3], 8) + appendSpace(args[4], 4);
    }

    public static String appendSpace(String input, int length) {
        if (input.length() < length) {
            return String.format("%1$-" + length + "s", input);
        } else {
            return input.substring(0, length);
        }
    }

}
