package com.javarush.test.level15.lesson12.home04;

/**
 * Created by Admin on 26.07.2016.
 */
public class Moon implements Planet {
    static Moon moon = new Moon();
    private Moon() {}
    public static Moon getInstance() {
        if (moon == null)
            return new Moon();
        else return moon;
    }

}
