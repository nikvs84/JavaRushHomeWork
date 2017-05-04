package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        //напишите тут ваш код
        ArrayList<int[]> list = new ArrayList<>();
//        try {

//            int[] a1 = new int[5];
//            for (int i = 0; i < a1.length; i++) {
//                a1[i] = i;
//            }
//            list.add(a1);
//            int[] a2 = new int[2];
//            for (int i = 0; i < a2.length; i++) {
//                a2[i] = i;
//            }
//            list.add(a2);
//            int[] a3 = new int[4];
//            for (int i = 0; i < a3.length; i++) {
//                a3[i] = i;
//            }
//            list.add(a3);
//            int[] a4 = new int[7];
//            for (int i = 0; i < a4.length; i++) {
//                a4[i] = i;
//            }
//            list.add(a4);
//            int[] a5 = new int[0];

//        }
//        catch (Exception e) {
//            System.out.println(e.getClass());
//        }
//        finally {
        int[] a = {1,2,3,4,5};
        int[] b = {1,2};
        int[] c = {1,2,3,4};
        int[] d = {1,2,3,4,5,6,7};
        int[] e = {};
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        return list;
//        }
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
