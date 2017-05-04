package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 18.10.2016.
 */
public class Order {
    private Tablet tablet;
    private List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        String result = "";
        if (dishes != null && !dishes.isEmpty()) {
            result += "Your order: [";
            for (int i = 0; i < dishes.size() - 1; i++) {
                result += dishes.get(i) + ", ";
            }
            result += dishes.get(dishes.size() - 1);
            result += "] of " + tablet;
        }
        return result;
    }

    public int getTotalCookingTime() {
        int time = 0;
        for (Dish dish : dishes) {
            time += dish.getDuration();
        }
        return time;
    }

    public boolean isEmpty() {
        return dishes == null || dishes.isEmpty();
    }
}
