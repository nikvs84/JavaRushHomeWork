package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter writer = new FileWriter(args[1]);
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String[] strings = reader.readLine().split(" ");
            for (String s : strings) {
                if (!s.matches("^\\D*$"))
                list.add(s);
            }
        }
        for (int i = 0; i < list.size() - 1; i++) {
            writer.write(list.get(i) + " ");
        }
        writer.write(list.get(list.size() - 1));
        reader.close();
        writer.close();
    }
}
