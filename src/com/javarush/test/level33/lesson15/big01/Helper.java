package com.javarush.test.level33.lesson15.big01;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Admin on 14.11.2016.
 */
public class Helper {
    public static String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        BigInteger bigInteger = new BigInteger(100, secureRandom);
        return bigInteger.toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
    public static void main(String[] args) {
        printMessage(generateRandomString());
    }
}
