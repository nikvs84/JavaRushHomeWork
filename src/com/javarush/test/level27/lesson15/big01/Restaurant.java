package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

/**
 * Created by Admin on 18.10.2016.
 */
public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Amigo");
        cook.addObserver(new Waitor());
        tablet.addObserver(cook);
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
