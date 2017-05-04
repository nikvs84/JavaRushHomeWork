package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        while (fileReader.ready())
            words.addAll(Arrays.asList(fileReader.readLine().split(" ")));
        fileReader.close();

        for(int i = 0; i < words.size(); i++)
        {
            for(int j = 0; j < words.size();)
            {
                if(words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i)
                {
                    Pair pair = new Pair();
                    pair.first = words.get(j);
                    pair.second = words.get(i);
                    result.add(pair);
                    words.remove(j);
                    words.remove(i);
                    j = 0;
                }
                else
                    j++;
            }
        }
/*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader= new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
        StringBuilder sb = new StringBuilder();
        if (reader.ready())
            sb.append(reader.readLine());
        while (reader.ready()) {
            sb.append(" ");
            sb.append(reader.readLine());
        }
        String[] ar = sb.toString().split(" ");
        for (int i = 0; i < ar.length; i++) {
            String s = ar[i];
            for (int j = i + 1; j < ar.length; j++) {
                String sss = ar[j];
                sb = new StringBuilder(sss);
                String ss = sb.reverse().toString();
                if (s.equals(ss)) {
                    Pair p = new Pair(s, ss);
                    if (!result.contains(p))
                    result.add(new Pair(s, ss));
                }
            }
        }

        for (Pair p : result) {
            System.out.println(p);
        }
*/
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }

        public Pair() {}

        public Pair(String s1, String s2) {
            this.first = s1;
            this.second = s2;
        }

        public int hashCode() {
            int result = this.first == null ? 0 : 31*this.first.hashCode();
            result = result += this.second == null ? 0 : this.second.hashCode();
            return result;
        }

        public boolean equals(Object o) {
            if (o == null)
                return false;
            Pair pair = (Pair) o;
            if (this.first != null ? !this.first.equals(pair.first) : pair.first != null)
                return false;
            if (this.second != null ? !this.second.equals(pair.second) : pair.second != null)
                return false;
            return true;
        }
    }

}
