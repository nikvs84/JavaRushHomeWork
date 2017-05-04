package com.javarush.test.level12.lesson02.task03;

/* Кот от кота, а собака от собаки
Переопределить метод getChild в классах Cat(кот) и Dog(собака), чтобы кот порождал кота, а собака – собаку.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Pet pet1 = new Cat();
        Pet cat = pet1.getChild();

        Pet pet2 = new Dog();
        Pet dog = pet2.getChild();
    }

    public static class Pet
    {
        public Pet getChild()
        {
            if (this instanceof Cat)
                return new Cat();
            else
//                if (this instanceof Dog)
                    return new Dog();
        }
    }

    public static class Cat extends Pet
    {

    }

    public static class Dog extends Pet
    {

    }
}
