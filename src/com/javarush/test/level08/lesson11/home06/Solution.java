package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human kid1 = new Human("kid1", true, 5);
        Human kid2 = new Human("kid2", false, 4);
        Human kid3 = new Human("kid3", true, 3);
        ArrayList<Human> child = new ArrayList<>();
        child.add(kid1);
        child.add(kid2);
        child.add(kid3);
        Human papa = new Human("papa", true, 31, child);
        Human mama = new Human("mama", false, 30, child);
        ArrayList<Human> childpap = new ArrayList<>();
        childpap.add(papa);
        ArrayList<Human> childmam = new ArrayList<>();
        childmam.add(mama);
        Human ded1 = new Human("ded1", true, 61, childpap);
        Human ded2 = new Human("ded1", true, 62, childmam);
        Human bab1 = new Human("bab1", true, 59, childpap);
        Human bab2 = new Human("bab1", true, 60, childmam);
        System.out.println(ded1.toString());
        System.out.println(ded2.toString());
        System.out.println(bab1.toString());
        System.out.println(bab2.toString());
        System.out.println(papa.toString());
        System.out.println(mama.toString());
        System.out.println(kid1.toString());
        System.out.println(kid2.toString());
        System.out.println(kid3.toString());
    }

    public static class Human
    {
        //напишите тут ваш код
        private String name;
        private boolean sex;
        private int age;
        private ArrayList<Human> children;

        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = new ArrayList<>();
        }
        public Human(String name, boolean sex, int age, ArrayList<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
