package com.javarush.test.level09.lesson02.task05;

/* Метод должен возвращать результат – глубину его стек-трейса
Написать метод, который возвращает результат – глубину его стек трейса – количество методов в нем (количество элементов в списке). Это же число метод должен выводить на экран.
*/

public class Solution
{
    public static int getStackTraceDeep()
    {
        //напишите тут ваш код
        int count = 0;
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        count = elements.length;
//        for (StackTraceElement el: elements) {
//            System.out.println(el.getMethodName());
//        }
        System.out.println(count);
        return  count;
    }

    public static void main(String[] args) {
        System.out.println(getStackTraceDeep());
    }
}
