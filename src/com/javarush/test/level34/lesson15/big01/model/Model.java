package com.javarush.test.level34.lesson15.big01.model;

import com.google.common.reflect.ClassPath;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by Admin on 22.11.2016.
 */
public class Model {
    EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("src/" + Model.class.getPackage().getName().replace("model", "res").replace('.', '/') + "/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollision(direction))
            return;
        int delta = GameObject.FIELD_SELL_SIZE;
        switch (direction) {
            case UP:
                player.move(0, -delta);
                break;
            case DOWN:
                player.move(0, delta);
                break;
            case LEFT:
                player.move(-delta, 0);
                break;
            case RIGHT:
                player.move(delta, 0);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            boolean b = player.isCollision(box, direction);
            if (b) {
                for (Box box2 : gameObjects.getBoxes()) {
                    if (box.isCollision(box2, direction))
                        return true;
                }
                if (checkWallCollision(box, direction))
                    return true;
                int delta = GameObject.FIELD_SELL_SIZE;
                switch (direction) {
                    case UP:
                        box.move(0, -delta);
                        break;
                    case DOWN:
                        box.move(0, delta);
                        break;
                    case LEFT:
                        box.move(-delta, 0);
                        break;
                    case RIGHT:
                        box.move(delta, 0);
                        break;
                }
            }

        }
        return false;
    }

    public void checkCompletion() {
        int homeWithBoxCount = 0;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    homeWithBoxCount++;
            }
        }
        if (homeWithBoxCount == gameObjects.getHomes().size())
            eventListener.levelCompleted(currentLevel);
    }
}
