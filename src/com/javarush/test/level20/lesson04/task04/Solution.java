package com.javarush.test.level20.lesson04.task04;

import java.io.*;

/* Как сериализовать static?
Сделайте так, чтобы сериализация класса ClassWithStatic была возможной
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public void serializeStatic(ObjectOutputStream objectOutputStream) throws IOException {
            PrintWriter writer = new PrintWriter(objectOutputStream);
//            objectOutputStream.writeChars(ClassWithStatic.staticString);
            writer.write(ClassWithStatic.staticString);
            writer.println();
            writer.close();
        }

        public void deserializedStatic(ObjectInputStream objectInputStream) throws IOException {
            String s;
            BufferedReader reader = new BufferedReader(new InputStreamReader(objectInputStream));
            ClassWithStatic.staticString = reader.readLine();
            reader.close();
        }
    }
}
