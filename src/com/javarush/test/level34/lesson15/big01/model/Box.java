package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Admin on 23.11.2016.
 */
public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        int newX = getX() + x;
        int newY = getY() + y;
        setX(newX);
        setY(newY);
    }

    @Override
    public void draw(Graphics graphics) {
        int newX = getX() - (getWidth() / 2);
        int newY = getY() - (getHeight() / 2);
//        graphics.setColor(Color.BLACK);
//        graphics.drawRect(newX, newY, getWidth(), getHeight());
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(newX, newY, getWidth(), getHeight());
    }
}
