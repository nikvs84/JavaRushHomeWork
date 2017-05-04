package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 23.11.2016.
 */
public class LevelLoader {
    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        if (level > 60)
            level = level - 60;
/*
        Collections.addAll(walls, new Wall(90, 70), new Wall(110, 70), new Wall(130, 70), new Wall(150, 90), new Wall(150, 110), new Wall(130, 110));
        Collections.addAll(boxes, new Box(110, 50), new Box(150, 50), new Box(70, 50));
        Collections.addAll(homes, new Home(70, 90), new Home(10, 10), new Home(130, 90));
        System.out.println(levels);
*/
        try (LineNumberReader reader = new LineNumberReader(new FileReader(levels.toFile()))) {
            String currentString;
            int lineNumber;
            int readedLevel;
            int rowCount;
            int lineCount;
            int skipLineCount = 5;
            while (reader.ready()) {
                currentString = reader.readLine();
                if (currentString.toLowerCase().startsWith("maze")) {
                    readedLevel = getDataFromLine(currentString, ":");
                    for (int i = 0; i < 1; i++) { reader.readLine();}
                    currentString = reader.readLine();
                    rowCount = getDataFromLine(currentString, ":");
                    currentString = reader.readLine();
                    lineCount = getDataFromLine(currentString, ":");
                    if (readedLevel != level) {
                        reader.setLineNumber(reader.getLineNumber() + lineCount + skipLineCount);
                        continue;
                    } else {
                        for (int i = 0; i < 3; i++) { reader.readLine(); }
                        for (int i = 0; i < lineCount; i++) {
                            currentString = reader.readLine();
                            int playerPositionX = addGameObjectsFromString(i, currentString, walls, boxes, homes);
                            if (playerPositionX != -1) {
                                int playerPositionY = i * GameObject.FIELD_SELL_SIZE + GameObject.FIELD_SELL_SIZE / 2;
                                player = new Player(playerPositionX, playerPositionY);
                            }
                        }
                        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
                        return gameObjects;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private int getDataFromLine(String string, String splitter) {
        String[] strings = string.split(splitter);
        return Integer.parseInt(strings[strings.length - 1].trim());
    }

    private int addGameObjectsFromString(int lineNumber, String string, Set<Wall> walls, Set<Box> boxes, Set<Home> homes) {
        int size = GameObject.FIELD_SELL_SIZE;
        int y = lineNumber * size + size / 2;
        int playerPosition = -1;
        for (int i = 0; i < string.length(); i++) {
            int x = i * size + size / 2;
            char c = string.charAt(i);
            switch (c) {
                case 'X':
                    walls.add(new Wall(x, y));
                    break;
                case '*':
                    boxes.add(new Box(x, y));
                    break;
                case '.':
                    homes.add(new Home(x, y));
                    break;
                case '&':
                    boxes.add(new Box(x, y));
                    homes.add(new Home(x, y));
                    break;
                case '@':
                    playerPosition = x;
                    break;
            }
        }
        return playerPosition;
    }
}
