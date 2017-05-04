package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName1));
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        while (reader.ready())
            list1.add(reader.readLine());
        reader.close();
        reader = new BufferedReader(new FileReader(fileName2));
        while (reader.ready())
            list2.add(reader.readLine());
        reader.close();
//        compareLines1(getCompareTable(list1, list2), lines);
        compareLines2(list1, list2, lines);
/*
        for (LineItem li : lines) {
            System.out.println(li.type + " " + li.line);
        }
*/

    }

    /*public static ArrayList<String[]> getCompareTable(List<String> list1, List<String> list2, List<LineItem> lines) {
        int size1 = list1.size();
        int size2 = list2.size();
        int min = 0, max = 0;

        min = size1 <= size2 ? size1 : size2;
        max = size1 >= size2 ? size1 : size2;

        ArrayList<String[]> al = new ArrayList<>(max);
        for (int i = 0; i < min; i++) {
            String[] s = new String[2];
            s[0] = list1.get(i);
            s[1] = list2.get(i);
            al.add(s);
        }
        if (size1 > size2) {
            for (int i = min; i < max; i++) {
                String[] s = new String[2];
                s[0] = list1.get(i);
                s[1] = "";
                al.add(s);
            }
        }
        else {
            for (int i = min; i <max; i++) {
                String[] s = new String[2];
                s[0] = "";
                s[1] = list2.get(i);
                al.add(s);
            }
        }
        return al;
    }
*/
   /* public static void compareLines1(ArrayList<String[]> al, List<LineItem> lines) {
        for (int i = 0; i < al.size(); i++) {
            String[] s = al.get(i);
            int c = s[0].compareTo(s[1]);
            if (c == 0)
                lines.add(new LineItem(Type.SAME, s[1]));
            else if (c < 0)
                lines.add(new LineItem(Type.ADDED, s[1]));
            else
                lines.add(new LineItem(Type.REMOVED, s[0]));
        }
    }
*/
    public static void compareLines2(List<String> list1, List<String> list2, List<LineItem> lines) {
        int ii = 0, jj = 0, i = 0, j = 0;
        while (i < list1.size()) {
            while (j < list2.size()) {
                if (list1.get(i).equals(list2.get(j))) {
                    lines.add(new LineItem(Type.SAME, list1.get(i)));
                    jj = j + 1;
                    j = jj;
                    i++;
                    break;
                }
                else {
                    jj = j + 1;
                    if (jj < list2.size()) {
                        if (list1.get(i).equals(list2.get(jj))) {
                            lines.add(new LineItem(Type.ADDED, list2.get(j)));
//                            lines.add(new LineItem(Type.SAME, list1.get(i)));
                            j = jj;

                            break;
                        }
                        else {
                            lines.add(new LineItem(Type.REMOVED, list1.get(i)));
//                            lines.add(new LineItem(Type.SAME, list1.get(++i)));

                            i++;
                            break;
                        }
                    }
                    else {
                        --jj;
                        lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                        i++;
                        break;
                    }

                    }
                }
            /*if (jj >= list2.size()) {
                if (i < list1.size())
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                i++;
            }*/
        }

        while (jj < list2.size()) {
            lines.add(new LineItem(Type.ADDED, list2.get(jj)));
            jj++;
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
