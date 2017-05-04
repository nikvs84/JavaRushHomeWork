package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by Admin on 28.07.2016.
 */
public abstract class DrinkMaker {
    public abstract void getRightCup();
    public abstract void putIngredient();
    public abstract void pour();
    public void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }
}
