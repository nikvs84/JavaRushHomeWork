package com.javarush.test.level21.lesson16.big01;

/**
 * Created by Admin on 30.09.2016.
 */
public class Horse {
    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move() {
        double rand = Math.random();
        this.distance += (this.speed * rand);
    }

    public void print() {
        StringBuffer sb = new StringBuffer();
        char c = '.';
        int count = (int) Math.round(this.distance);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        sb.append(this.name);
        String result = sb.toString();
        System.out.println(result);
    }
}
