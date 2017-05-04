package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 14.11.2016.
 */
public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String string: strings) {
            result.add(shortener.getId(string));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long l: keys) {
            result.add(shortener.getString(l));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Set<Long> longSet = new HashSet<>();
        Date start = new Date();
        longSet = getIds(shortener, stringSet);
        Date stop = new Date();
        long dist = stop.getTime() - start.getTime();
        Helper.printMessage(String.valueOf(dist));
        Set<String> newStringSet = new HashSet<>();
        long start1 = new Date().getTime();
        newStringSet = getStrings(shortener, longSet);
        long stop1 = new Date().getTime();
        dist = stop1 - start1;
        Helper.printMessage(String.valueOf(dist));

        boolean b = stringSet.equals(newStringSet);
        if (b) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy( new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }
}

