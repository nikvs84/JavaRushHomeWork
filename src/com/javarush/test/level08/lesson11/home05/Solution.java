package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
//        ArrayList<Character> array = new ArrayList<>();
//        array.add(Character.toUpperCase(s.charAt(0)));
//        for (int i = 1; i < s.length(); i++) {
//            array.add(s.charAt(i));
//            char c = array.get(i);
//            if (array.get(i-1) == ' ' && ((c>='a'&& c<='z')||(c>='а'&& c<='я'))){
//                array.set(i, Character.toUpperCase(c));
//            }
//        }
        //напишите тут ваш код
//        s = "";
//        for (int i = 0; i < array.size(); i++) {
//            s = s + array.get(i);
//        }
        char[] array = new char[s.length()];
        array[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            array[i] = s.charAt(i);
            char c = array[i];
            if (array[i-1] == ' ' && ((c>='a'&& c<='z')||(c>='а'&& c<='я')))
                array[i] = Character.toUpperCase(c);
        }
        s = new String(array);
//        for (char c: array) {
//            System.out.print(c);
//        }
        System.out.println(s);
    }


}
