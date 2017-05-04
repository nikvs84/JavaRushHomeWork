package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by Admin on 27.10.2016.
 */
public class Soldier extends Human {
    public Soldier(String name, int age) {
        super(name, age);
    }

    public void live() {
            fight();
    }

    public void fight() {
    }

}
