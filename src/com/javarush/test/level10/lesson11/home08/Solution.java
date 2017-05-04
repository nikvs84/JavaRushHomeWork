package com.javarush.test.level10.lesson11.home08;

import java.io.IOException;
import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args) throws IOException {
        try {
            ArrayList<String>[] arrayOfStringList = createList();
            printList(arrayOfStringList);
        }
        catch (Exception e) {
            System.out.println(e.getClass());
            throw e;
        }
    }

    public static ArrayList<String>[] createList() throws IOException {
        //напишите тут ваш код
        ArrayList<String>[] list = new ArrayList[5];
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++){
            list[i] = new ArrayList<String>();
            for (int j = 0; j < 5; j++){
                list[i].add(i + " " + j);
            }
        }

        return list;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}