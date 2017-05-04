package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by Admin on 23.11.2016.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int newX = getX() - (getWidth() / 2);
        int newY = getY() - (getHeight() / 2);
        graphics.setColor(Color.BLUE);
        graphics.fillRect(newX, newY, getWidth(), getHeight());
    }
}
