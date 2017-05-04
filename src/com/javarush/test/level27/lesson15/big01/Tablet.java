package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Admin on 18.10.2016.
 */
public class Tablet extends java.util.Observable {
    private final int number;
    private List<Cook> cooks;
    private AdvertisementManager advertisementManager;

    static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
        cooks = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    public void createOrder() {
        try {
            Order order = new Order(this);
            if (!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
                advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                try {
                    advertisementManager.processVideos();
                } catch (NoVideoAvailableException e) {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }
}
