package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human dedf = new Human("Dedf", true, 65);
        Human dedm = new Human("Dedm", true, 64);
        Human babf = new Human("Babf", false, 63);
        Human babm = new Human("Babm", false, 62);
        Human papa = new Human("Papa", true, 30, dedf, babf);
        Human mama = new Human("Mama", false, 29, dedm, babm);
        Human kid1 = new Human("Kid1", true, 7, papa, mama);
        Human kid2 = new Human("Kid2", true, 5, papa, mama);
        Human kid3 = new Human("Kid3", true, 3, papa, mama);
        System.out.println(dedf);
        System.out.println(dedm);
        System.out.println(babf);
        System.out.println(babm);
        System.out.println(papa);
        System.out.println(mama);
        System.out.println(kid1);
        System.out.println(kid2);
        System.out.println(kid3);
    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human father, mother;

        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
        public Human(String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
