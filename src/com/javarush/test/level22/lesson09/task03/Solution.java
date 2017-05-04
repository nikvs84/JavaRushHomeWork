package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready())
        {
            String[] s = reader.readLine().split("\\s");
            Collections.addAll(list, s);
        }
        reader.close();
        String[] words = new String[list.size()];
        words = list.toArray(words);
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
        scanner.close();

    }

    public static StringBuilder getLine(String... words) {
        if (words == null) {return new StringBuilder();}
        if (words.length==0) {return new StringBuilder();}

        //String[] array = i.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s: words) {
            arrayList.add(s);
        }
        int neededCounter = arrayList.size();
        StringBuilder resultBuilder = new StringBuilder();
        while (true)
        {
            int wordCounter = 0;
            ArrayList<String> temp = new ArrayList<>(arrayList);
            Collections.shuffle(temp);
            StringBuilder tempBuilder = new StringBuilder();
            tempBuilder.append(arrayList.get(0));
            temp.remove(arrayList.get(0));
            boolean canAdd = true;

            while (canAdd)
            {
                {
                    ArrayList<String> toDelete = new ArrayList<>();
                    for (String s : temp)
                    {
                        StringBuilder word = new StringBuilder(s);
                        // если конец стрингбилдера равен первой букве другого слова
                        if (tempBuilder.substring(tempBuilder.length() - 1).equals(word.reverse().substring(s.length() - 1).toLowerCase()))
                        {
                            tempBuilder.append(" " + s);
                            toDelete.add(s);
                            wordCounter++;
                            continue;
                        }
                        // если начало стрингбилдера равно последней букве другого слова "Киев Вена" - "Нью Йорк"
                        if (tempBuilder.toString().substring(0, 1).toLowerCase().equals(s.substring(s.length() - 1).toLowerCase()))
                        {
                            tempBuilder.reverse().append(" " + word);
                            tempBuilder.reverse();
                            toDelete.add(s);
                            wordCounter++;
                            continue;
                        }
                    }
                    // удаляем уже вставленные слова
                    for (String s : toDelete)
                    {
                        temp.remove(s);
                    }
                    toDelete.clear();
                    // если ни к концу ни к началу нельзя добавить символ - break;
                    for (String s : temp)
                    {
                        StringBuilder word = new StringBuilder(s);
                        if (!tempBuilder.substring(tempBuilder.length() - 1).equals(word.reverse().substring(s.length() - 1).toLowerCase()) &&
                                !tempBuilder.toString().substring(0, 1).toLowerCase().equals(s.substring(s.length() - 1).toLowerCase()))
                        {
                            canAdd = false;
                        }
                    }
                }
                resultBuilder = tempBuilder;
                if (wordCounter==neededCounter-1) {
                    return resultBuilder;
                }
            }
        }
    }
}

//ПИПЕЦ!!!!! Все работает. Не проходит проверку.

/*
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));

        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            list.addAll(Arrays.asList(reader.readLine().split(" ")));
        }
        reader.close();
        //Сортировка для красоты!
        Collections.sort(list);
        //Убираем повторяющиеся слова
        for (int i = 0; i < list.size() - 1; i++) {
            String s1 = list.get(i);
            String s2 = list.get(i + 1);
            if (s1.equals(s2)) {
                list.remove(i + 1);
                --i;
            }
        }
        //Запихиваем слова в массив, чтобы передать его в функцию getLine
        String [] strings = new String[list.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = list.get(i);
        }

        StringBuilder result = getLine(strings);
        System.out.println(result.toString());
//        System.out.println("\uFEFF");
    }

    public static StringBuilder getLine(String... words) {

        StringBuilder sb = new StringBuilder();
        if (words == null || words.length == 0)
            return sb;

        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(words));
        words[0] = list.get(0);
        list.remove(0);
        for (int i = 0; i < words.length - 1; i++) {
            String s1 = words[i];
            String st1 = s1.substring(s1.length() - 1);
            for (int j = 0; j < list.size(); j++) {
                String s2 = list.get(j);
                String st2 = s2.substring(0, 1);
                if (st1.equalsIgnoreCase(st2)) {
                    words[i + 1] = list.get(j);
                    list.remove(j);
                    break;
                }
            }
        }
        sb.append(words[0]);
        for (int i = 1; i < words.length; i++) {
            sb.append(" " + words[i]);
        }
          return sb;
    }
}
*/

