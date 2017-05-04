package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File mainDir = new File(root);
        return reviewDir(mainDir);
    }

    private static List<String> reviewDir(File dir) {
        List<String> result = new ArrayList<>();
        Queue<File> fileQueue = new ArrayDeque<>();
        fileQueue.add(dir);
        do {
            File currentFile = fileQueue.poll();
            for (File f : currentFile.listFiles()) {
                if (f.isFile()) {
                    result.add(f.getAbsolutePath());
                } else if (f.isDirectory()) {
                    fileQueue.add(f);
                }
            }
        } while (fileQueue.size() > 0);
        return result;
    }

    public static void main(String[] args) {
        try {
            for (String fileName: getFileTree("f:/Javarush")) {
                System.out.println(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
