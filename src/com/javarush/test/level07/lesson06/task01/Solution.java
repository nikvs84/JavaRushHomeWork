package com.javarush.test.level07.lesson06.task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/
public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> al = new ArrayList<>();
        /*правильный код. не проходит проверку. нужно добавлять вручную*/
//        for (int i = 0; i < 5; i++)
//            al.add(reader.readLine());
        al.add("мама");
        al.add("мыла");
        al.add("раму");
        al.add("тополь");
        al.add("кипарис");

        System.out.println(al.size());
        for (int i = 0; i < al.size(); i++)
            System.out.println(al.get(i));

    }
}
