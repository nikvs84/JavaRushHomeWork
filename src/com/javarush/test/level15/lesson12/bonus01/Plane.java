package com.javarush.test.level15.lesson12.bonus01;

/**
 * Created by Admin on 28.07.2016.
 */
public class Plane implements Flyable {
    int passCount;
    @Override
    public void fly() {
        System.out.println("Plane fly");
    }

    public Plane(int passCount) {
        this.passCount = passCount;
    }
}
