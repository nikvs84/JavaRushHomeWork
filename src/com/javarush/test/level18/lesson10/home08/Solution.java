package com.javarush.test.level18.lesson10.home08;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/
public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (String fileName = sc.nextLine(); !fileName.equals("exit"); fileName = sc.nextLine()) {
            Thread th = new ReadThread(fileName);
            th.start();
            try {
                th.join();
            }catch (Exception e) {}
        }

        sc.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут

        public void run() {
            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
                int nextByte;
                int[] bytesCount = new int[256];
                while ((nextByte = in.read()) != -1) {
                    if (nextByte < -1) throw new RuntimeException();
                    bytesCount[nextByte]++;
                }
                int maxI = 0;
                for (int i = 1; i < bytesCount.length; i++)
                    if (bytesCount[i] > bytesCount[maxI])
                        maxI = i;
                synchronized (Solution.class) {
                    resultMap.put(fileName, maxI);
                }
                //System.out.println(maxI);
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File \"" + fileName + "\" not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Read next byte error. File: \"" + fileName + "\"");
                e.printStackTrace();
            }
        }
    }
}
/*
public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        ArrayList<Thread> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        int i = 0;
        while (!"exit".equals(fileName)) {
            list.add(new ReadThread(fileName));
            list.get(i).start();
            fileName = reader.readLine();
            i++;
        }
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        private FileInputStream inputStream;
        private String fileName;
        public void run() {
            try {
                inputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int fileSize=0;
            try {
                fileSize = inputStream.available();
                if (fileSize > 0) {
                    int[] bytes = new int[256];
                    while (inputStream.available() > 0) {
                        bytes[inputStream.read()]++;
                    }
                    int max = 0;
                    byte index = 0;
                    for (int i = 0; i < bytes.length; i++) {
                        if (bytes[i] > max)
                            index = (byte) i;
                    }
                    synchronized (Solution.class) {
                        resultMap.put(fileName, (int) index);
                    }
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
