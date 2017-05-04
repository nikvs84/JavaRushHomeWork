package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int a=Integer.parseInt(reader.readLine());
        int b=Integer.parseInt(reader.readLine());
        int c=Integer.parseInt(reader.readLine());
        int d;
        if (a<c)
        {
            d = a;
            a = c;
            c = d;
        }

            if (a<b){
                d=a;
                a=b;
                b=d;
            }
            if (b<c){
                d=b;
                b=c;
                c=d;
            }


        System.out.println(a+" "+b+" "+c);
//        System.out.println(c);
//        System.out.println(b);
//        System.out.println(a);
        //напишите тут ваш код

    }
}
