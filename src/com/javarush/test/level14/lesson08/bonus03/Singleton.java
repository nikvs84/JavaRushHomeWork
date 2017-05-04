package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by Admin on 22.07.2016.
 */
public final class Singleton {
    private static Singleton singleton = new Singleton();
    static Singleton getInstance() {
        if (singleton == null)
        return new Singleton();
        else
            return singleton;
    }
    private Singleton() {
    }
}
