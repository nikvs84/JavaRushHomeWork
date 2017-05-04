package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        Man man1 = new Man("Vasja", 25, "Moskva");
        Man man2 = new Man("Misha", 28, "Surgut");
        
        Woman woman1 = new Woman("Alla", 23, "Minsk");
        Woman woman2 = new Woman("Elena", 25, "Sevastopol");
        //создай по два объекта каждого класса тут

        System.out.println(man1.name+" "+man1.age+" "+man1.address);
        System.out.println(man2.name+" "+man2.age+" "+man2.address);
        System.out.println(woman1.name+" "+woman1.age+" "+woman1.address);
        System.out.println(woman2.name+" "+woman2.age+" "+woman2.address);
        //выведи их на экран тут
    }

    public static class Man{
        private String name = null, address = null;
        private int age=18;
        
        public Man(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public Man(String name){
            this.name = name;
        }
        public Man(String name, int age){
            this.name = name;
            this.age = age;
        }
        public Man(String name, String address){
            this.name = name;
            this.address = address;
        }
        public Man(int age, String address){
            this.age = age;
            this.address = address;
        }
        public Man(int age){
            this.age = age;
        }
    }
    public static class Woman{
        private String name = null, address = null;
        private int age=18;
        
        public Woman(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }
        public Woman(String name){
            this.name = name;
        }
        public Woman(String name, int age){
            this.name = name;
            this.age = age;
        }
        public Woman(String name, String address){
            this.name = name;
            this.address = address;
        }
        public Woman(int age, String address){
            this.age = age;
            this.address = address;
        }
        public Woman(int age){
            this.age = age;
        }
    }
    //добавьте тут ваши классы
}
