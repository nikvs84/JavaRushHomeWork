package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by Admin on 27.10.2016.
 */
public class Cabriolet extends Car {

    public Cabriolet(int numberOfPassengers) {
        super(Car.CABRIOLET, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        final int MAX_CABRIOLET_SPEED = 90;
        return MAX_CABRIOLET_SPEED;
    }
}