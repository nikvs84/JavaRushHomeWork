package com.javarush.test.level37.lesson04.task01;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 08.12.2016.
 */
public class FileRenamerForL27Big01 {
    public static void main(String[] args) {
        renameJavaFilesInFolder("F:\\Javarush\\level27_big01");
    }

    public static void renameJavaFilesInFolder(String dirName) {
        List<Path> pathList = new ArrayList<>();
        File dir = new File(dirName);
        for (File file:dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".java");
            }
        })) {
            if (file.getName().startsWith("big01_")) {
                int index = file.getName().lastIndexOf('_');
                file.renameTo(new File(file.getParent() + file.separator + file.getName().substring(index + 1)));
            }
        }
    }
}
