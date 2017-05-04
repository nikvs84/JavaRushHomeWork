package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human h1 = new Human();
        Human h2 = new Human("a");
        Human h3 = new Human("a", "b");
        Human h4 = new Human("a", "b", "c");
        Human h5 = new Human(1);
        Human h6 = new Human(1, 2);
        Human h7 = new Human(1, 2, 3);
        Human h8 = new Human("a", 3);
        Human h9 = new Human("a", "b", 3);
        Human h10 = new Human("a", "b", "c", 3);
    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        private String s1, s2, s3;
        private int i1, i2, i3;
        public Human(){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = 0;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(String s1){
            this.s1 = s1;
            this.s2 = "";
            this.s3 = "";
            this.i1 = 0;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(String s1, String s2){
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = "";
            this.i1 = 0;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(String s1, String s2, String s3){
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.i1 = 0;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(int i1){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(int i1, int i2){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = i2;
            this.i3 = 0;
        }
        public Human(int i1, int i2, int i3){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = i2;
            this.i3 = i3;
        }
        public Human(String s1, int i1){
            this.s1 = s1;
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(String s1, String s2, int i1){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = 0;
            this.i3 = 0;
        }
        public Human(String s1, String s2, String s3, int i1){
            this.s1 = "";
            this.s2 = "";
            this.s3 = "";
            this.i1 = i1;
            this.i2 = 0;
            this.i3 = 0;
        }
    }
}
