package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом, каждая строка должна заканчиваться пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
//            String ss="", sg="";
            StringBuffer sg = new StringBuffer();
            StringBuffer ss = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                if (isVowel(s.charAt(i)))
                    sg.append(s.charAt(i)); // = sg + s.charAt(i);
                else
                    ss.append(s.charAt(i));// = ss + s.charAt(i);
            }
            for (int i = 0; i < sg.length(); i++) {
                System.out.print(sg.charAt(i) + " ");
            }
            System.out.println();
            for (int i = 0; i < ss.length(); i++) {
                System.out.print(ss.charAt(i) + " ");
            }
            System.out.println();
        }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
