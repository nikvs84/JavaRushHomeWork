package com.javarush.test.level03.lesson04.task03;

/* StarCraft
Создать 10 зергов, 5 протосов и 12 терран.
Дать им всем уникальные имена.
*/

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код

        Zerg Zerg1=new Zerg();
        Zerg1.name="Z1";
        Zerg Zerg2=new Zerg();
        Zerg2.name="Z2";
        Zerg Zerg3=new Zerg();
        Zerg3.name="Z3";
        Zerg Zerg4=new Zerg();
        Zerg4.name="Z4";
        Zerg Zerg5=new Zerg();
        Zerg5.name="Z5";
        Zerg Zerg6=new Zerg();
        Zerg6.name="Z6";
        Zerg Zerg7=new Zerg();
        Zerg7.name="Z7";
        Zerg Zerg8=new Zerg();
        Zerg8.name="Z8";
        Zerg Zerg9=new Zerg();
        Zerg9.name="Z9";
        Zerg Zerg10=new Zerg();
        Zerg10.name="Z10";

        Protos Protos1=new Protos();
        Protos1.name="P1";
        Protos Protos2=new Protos();
        Protos2.name="P2";
        Protos Protos3=new Protos();
        Protos3.name="P3";
        Protos Protos4=new Protos();
        Protos4.name="P4";
        Protos Protos5=new Protos();
        Protos5.name="P5";

        Terran Terran1=new Terran();
        Terran1.name="T1";
        Terran Terran2=new Terran();
        Terran2.name="T2";
        Terran Terran3=new Terran();
        Terran3.name="T3";
        Terran Terran4=new Terran();
        Terran4.name="T4";
        Terran Terran5=new Terran();
        Terran5.name="T5";
        Terran Terran6=new Terran();
        Terran6.name="T6";
        Terran Terran7=new Terran();
        Terran7.name="T7";
        Terran Terran8=new Terran();
        Terran8.name="T8";
        Terran Terran9=new Terran();
        Terran9.name="T9";
        Terran Terran10=new Terran();
        Terran10.name="T10";
        Terran Terran11=new Terran();
        Terran11.name="T11";
        Terran Terran12=new Terran();
        Terran12.name="T12";
        //System.out.print(Zerg2.Get_Name());

    }



    private static class Zerg
    {
        public String name;
        /*Zerg(String name)
        {
         this.name=name;
        }

        public String Get_Name()
        {
            return name;
        }*/
    }

    public static class Protos
    {
        public String name;
       /* Protos(String name)
        {
            this.name=name;
        }

        public String Get_Name()
        {
            return name;
        }*/
    }

    public static class Terran
    {
        public String name;
        /*Terran(String name)
        {
            this.name=name;
        }

        public String Get_Name()
        {
            return name;
        }*/
    }
}