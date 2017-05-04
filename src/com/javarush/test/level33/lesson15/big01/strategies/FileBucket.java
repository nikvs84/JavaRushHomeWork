package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Admin on 14.11.2016.
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("tmp", null);
            path.toFile().deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(entry);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if (getFileSize() > 0)
            try {
                try (FileInputStream fis = new FileInputStream(path.toFile())) {
                    try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                        return (Entry) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
