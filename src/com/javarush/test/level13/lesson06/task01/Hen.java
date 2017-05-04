package com.javarush.test.level13.lesson06.task01;

/**
 * Created by Admin on 18.07.2016.
 */
public abstract class Hen/* implements Country*/ {

//    public String country;
//    public int countOfEggsPerMonth;

    public abstract int getCountOfEggsPerMonth();

    public String getDescription() {
        return "Я - курица.";
    }
}
