package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Admin on 23.11.2016.
 */
public class Player extends CollisionObject implements Movable {

    public Player(int x, int y) {
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
//        graphics.drawOval(newX, newY, getWidth(), getHeight());
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(newX, newY, getWidth(), getHeight());
    }
}
