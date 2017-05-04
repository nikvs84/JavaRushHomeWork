package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        String str;
        String[] strMass;
        double tmp;

        while ((str = fileReader.readLine()) != null) {
            strMass = str.split(" ");
            for (String strMas : strMass)
            {
                tmp = Math.round(Double.parseDouble(strMas));
                numbers.add((int)tmp);
            }
        }
        fileReader.close();

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2));
        String num = "";
        for (int a : numbers)
            num = num+a+" ";
        fileWriter.write(num);
        fileWriter.close();
/*        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader = new BufferedReader(new FileReader(filename1));
        StringBuffer sb = new StringBuffer();
        if (!reader.ready()) return;
        while (reader.ready()) {
            sb.append(reader.readLine() + " ");
        }
        String s = sb.toString().trim();
        while (s.indexOf("  ") > 0)
            s = s.replace("  ", " ");
        String[] as = s.split(" ");
        for (int i = 0; i < as.length; i++) {
            double d = Double.parseDouble(as[i]);
            d = Math.round(d);
            as[i] = "" + (int) d;
        }
        int l = as.length - 1;
        sb = new StringBuffer();
        for (int i = 0; i < l; i++) {
            sb.append(as[i] + " ");
        }
        sb.append(as[as.length - 1]);
        s = sb.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename2));
        writer.write(s);
        writer.flush();
        reader.close();
        writer.close();*/
    }
}
