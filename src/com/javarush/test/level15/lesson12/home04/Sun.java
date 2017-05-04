package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Admin on 26.07.2016.
 */
public class Sun implements Planet {
    static Sun sun = new Sun();
    private Sun() {}
    public static Sun getInstance() {
        if (sun == null)
        return new Sun();
        else return sun;
    }
}
