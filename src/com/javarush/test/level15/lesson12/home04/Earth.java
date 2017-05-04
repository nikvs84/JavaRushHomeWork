package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Admin on 26.07.2016.
 */
public class Earth implements Planet {
    static Earth earth = new Earth();
    private Earth() {}
    public static Earth getInstance() {
        if (earth == null)
            return new Earth();
        else return earth;
    }

}
