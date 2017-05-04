package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
*/

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String directoryPath = args[0];
        String resultFileAbsolutePath = args[1];
        //String directoryPath = "src/main/resources/level31/lesson02/home01";
        //String resultFileAbsolutePath = "src/main/resources/level31/lesson02/home01/res.txt";
        String allFilesContent = "allFilesContent.txt";

        saveFilesLessThan50Bytes(directoryPath);

        // rename file
        Path source = Paths.get(resultFileAbsolutePath);
        Path newResPath = Files.move(source, source.resolveSibling(allFilesContent));

        // delete resultFileAbsolutePath if list contains it
        lessThan50BytesFiles.remove(new File(resultFileAbsolutePath));

        // sort file by name
        Collections.sort(lessThan50BytesFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String fileName1 = o1.getName();
                String fileName2 = o2.getName();
                return fileName1.compareTo(fileName2);
            }
        });

        // write to result file
        BufferedWriter fout = new BufferedWriter(new FileWriter(newResPath.toFile()));
        for (File file : lessThan50BytesFiles) {
            BufferedReader fin = new BufferedReader(new FileReader(file));
            while (fin.ready()) {
                fout.write(fin.readLine());
                fout.newLine();
            }
            fin.close();
        }
        fout.close();
    }

    private static List<File> lessThan50BytesFiles = new ArrayList<>();

    private static void saveFilesLessThan50Bytes(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();

        if (files == null) {
            // it is not directory
            return;
        } else if (files.length == 0) {
            // directory is empty
            dir.delete();
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    // nested directory
                    saveFilesLessThan50Bytes(file.getAbsolutePath());
                } else {
                    if (file.length() > 50) {
                        file.delete();
                    } else {
                        lessThan50BytesFiles.add(file);
                    }
                }
            }
        }
    }
}
/*
* Все работает, используя многопоточную рекурсию, но не проходит проверку
* */
/*public class Solution {
    public static void main(String[] args) {
        List<File> resultFileList = new CopyOnWriteArrayList<>();
        if (args.length < 2) {
            args = new String[2];
            args[0] = "f:\\Javarush";
            args[1] = "f:\\Javarush\\resultFileAbsolutePath.txt";
        }
        File mainDir = new File(args[0]);
        File resultFile = new File(args[1]);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        resultFileList = forkJoinPool.invoke(new FileFinder(mainDir, resultFile));
        Collections.sort(resultFileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        printResultToFile(resultFile, resultFileList);

        String newFileName = resultFile.getParent() + "\\allFilesContent.txt";
        resultFile.renameTo(new File(newFileName));
    }

    private static void printResultToFile(File file, List<File> list) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size() - 1; i++) {
            writer.print(list.get(i).getName());
            writer.println();
        }
        writer.print(list.get(list.size() - 1).getName());
        writer.close();
    }

    private static class FileFinder extends RecursiveTask<List<File>> {
        private List<File> fileList = new CopyOnWriteArrayList<>();
        File file;
        File resultFile;

        public FileFinder(File file, File resultFile) {
            this.file = file;
            this.resultFile = resultFile;
        }

        @Override
        protected List<File> compute() {
            List<File> fileList = new CopyOnWriteArrayList<>();
            if (file.isFile() && !file.equals(resultFile)) {
                if (file.length() > 50) {
                    file.delete();
                } else {
                    fileList.add(file);
                }
            } else if (file.isDirectory()){
                if (file.list().length == 0) {
                    file.delete();
                } else {
                    List<FileFinder> subTaskList = new ArrayList<>();
                    for (File f : file.listFiles()) {
                        FileFinder fileFinder = new FileFinder(f, resultFile);
                        fileFinder.fork();
                        subTaskList.add(fileFinder);
                    }
                    for (FileFinder fileFinder : subTaskList) {
                        fileList.addAll(fileFinder.join());
                    }
                }
            }
            return fileList;
        }
    }
}*/
