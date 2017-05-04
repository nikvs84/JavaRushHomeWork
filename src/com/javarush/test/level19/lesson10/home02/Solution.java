package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        HashMap<String, Double> map = new HashMap<>();
        while (reader.ready()) {
            String[] strings = reader.readLine().split(" ");
            double d = Double.parseDouble(strings[1]);
            if (map.containsKey(strings[0])) {
                d = d + map.get(strings[0]);
                map.put(strings[0], d);
            } else map.put(strings[0], d);
        }
        reader.close();
        Double max = Double.MIN_VALUE;
        Double value;
        for (String s : map.keySet()) {
            value = map.get(s);
            if (value > max) {
                max = value;
            }
        }

        for (String s1 : map.keySet()) {
            value = map.get(s1);

            if (value == max) {
                System.out.println(s1);
            }
        }
/*        Iterator iterator = map.entrySet().iterator();
        HashMap.Entry<String, Double> max = (HashMap.Entry<String, Double>) iterator.next();
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (max.getValue() < pair.getValue())
                max = pair;
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue().compareTo(max.getValue()) == 0)
                System.out.println(pair.getKey());
        }*/
    }
}
