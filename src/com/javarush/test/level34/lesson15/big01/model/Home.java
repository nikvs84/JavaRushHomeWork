package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Admin on 23.11.2016.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }

    @Override
    public void draw(Graphics graphics) {
        int newX = getX() - (getWidth() / 2);
        int newY = getY() - (getHeight() / 2);
        graphics.setColor(Color.RED);
        graphics.drawOval(newX, newY, getWidth(), getHeight());
    }
}
