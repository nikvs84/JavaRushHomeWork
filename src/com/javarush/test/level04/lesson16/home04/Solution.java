package com.javarush.test.level04.lesson16.home04;

import java.io.*;

/* Меня зовут 'Вася'...
Ввести с клавиатуры строку name.
Ввести с клавиатуры дату рождения (три числа): y, m, d.
Вывести на экран текст:
«Меня зовут name
Я родился d.m.y»
Пример:
Меня зовут Вася
Я родился 15.2.1988
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String name=reader.readLine(), /*date=reader.readLine(),*/ y=reader.readLine(), m=reader.readLine(), d=reader.readLine();
//                y="", m="", d="";
//        int l=date.length();
//        int i=0;
//        while (date.charAt(i)!=','){
//            y+=date.charAt(i);
//            i++;
//        }
//        i++;
//        while (date.charAt(i)!=','){
//            m+=date.charAt(i);
//            i++;
//        }
//        i++;
//        while (i<l){
//            d+=date.charAt(i);
//            i++;
//        }
//        i++;
        System.out.println("Меня зовут "+name);
        System.out.println("Я родился "+d+"."+m+"."+y);
        //напишите тут ваш код
    }
}
