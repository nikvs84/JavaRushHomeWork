package com.javarush.test;

/**
 * Created by Admin on 12.12.2016.
 */
public class MainClass {
    public static void main(String[] args) {
        int n = 100;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
