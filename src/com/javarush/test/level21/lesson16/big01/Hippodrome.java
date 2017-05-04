package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Admin on 30.09.2016.
 */
public class Hippodrome {

    private static ArrayList<Horse> horses = new ArrayList<>();

    public static Hippodrome game;

    public static void main(String[] args) {
        game = new Hippodrome();
        ArrayList<Horse> list = game.getHorses();
        list.add(new Horse("Horse1", 3, 0));
        list.add(new Horse("Horse2", 3, 0));
        list.add(new Horse("Horse3", 3, 0));
        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses() {
        return this.horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        Horse result = game.horses.get(0);
        Horse tempHorse;
        for (int i = 0; i < game.horses.size(); i++) {
            tempHorse = game.horses.get(i);
            double resDist = result.getDistance();
            double tempDist = tempHorse.getDistance();
            if (Double.compare(tempDist, resDist) > 0) {
                result = tempHorse;
            }
        }
        return result;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
