package com.javarush.test.level20.lesson04.task05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution implements Serializable {
    public static class Object implements Serializable {
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.write(Solution.countStrings);
    }

    public void deserializeStatic(ObjectInputStream objectInputStream) throws IOException {
        Solution.countStrings = objectInputStream.readInt();
    }

    public static class String implements Serializable {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
