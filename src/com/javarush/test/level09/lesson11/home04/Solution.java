package com.javarush.test.level09.lesson11.home04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Solution {

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
//            date = new Date(reader.readLine());
            date = new SimpleDateFormat("MM/dd/yyyy").parse(reader.readLine());
            System.out.println(sdf.format(date).toUpperCase());
        }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
