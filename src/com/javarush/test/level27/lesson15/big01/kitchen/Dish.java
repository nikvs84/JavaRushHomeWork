package com.javarush.test.level27.lesson15.big01.kitchen;

/**
 * Created by Admin on 18.10.2016.
 */
public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);
    private int duration;
    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String result = "";
        Dish[] dishes = Dish.values();
        for (int i = 0; i < dishes.length - 1; i++) {
            result += dishes[i] + ", ";
        }
        result += dishes[dishes.length - 1];
        return result;
    }

}
