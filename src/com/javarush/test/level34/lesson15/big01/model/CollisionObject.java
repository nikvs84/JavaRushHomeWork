package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by Admin on 23.11.2016.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int newX = getX();
        int newY = getY();
        switch (direction) {
            case UP:
                newY -= FIELD_SELL_SIZE;
                break;
            case DOWN:
                newY += FIELD_SELL_SIZE;
                break;
            case LEFT:
                newX -= FIELD_SELL_SIZE;
                break;
            case RIGHT:
                newX += FIELD_SELL_SIZE;
                break;
        }
        return (gameObject.getX() == newX && gameObject.getY() == newY);
    }
}
