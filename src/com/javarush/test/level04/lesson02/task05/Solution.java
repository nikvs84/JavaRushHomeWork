package com.javarush.test.level04.lesson02.task05;

/* Подсчитать количество котов
Написать код, чтобы правильно считалось количество созданных котов (count) и на экран выдавалось правильно их количество.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Cat cat1 = getnewCat();
        //напишите тут ваш код

        Cat cat2 = getnewCat();
        //напишите тут ваш код

        System.out.println("Cats count is " + Cat.count);
    }
    public static class Cat
    {
        public static int count = 0;
    }

    public static Cat getnewCat(){
        Cat.count++;
        return new Cat();
    }
    {

    }
}