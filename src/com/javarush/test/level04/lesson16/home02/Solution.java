package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
//        int a=Integer.parseInt(reader.readLine());
//        int b=Integer.parseInt(reader.readLine());
//        int c=Integer.parseInt(reader.readLine());
//        if ((a<b&&b<c)||(a>b&&b>c))
//            System.out.println(b);
//        else
//            if (a<c&&c<b)
//                a=c;
//        double numbers[] = new double[3];
//        double[] numbers={Double.parseDouble(reader.readLine()),Double.parseDouble(reader.readLine()),Double.parseDouble(reader.readLine())};
        int[] numbers={Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine())};
        for (int i = 0; i < 2; i++)
        {
            //double d;
            int d;
            for (int j = i+1; j < 3; j++)
            {
                if (numbers[i]>numbers[j]){
                    d=numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=d;
                }
            }
//            numbers[i]=Double.parseDouble(reader.readLine());
//            System.out.println("A["+i+"] = "+numbers[i]);

        }
        System.out.println(numbers[1]);
//        for (int i = 0; i < 3; i++)
//        {
//            System.out.println(numbers[i]);
//        }
        //напишите тут ваш код
    }
}
