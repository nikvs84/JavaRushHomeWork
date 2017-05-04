package com.javarush.test.level31.lesson08.home01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* Null Object Pattern
Почитайте на вики про паттерн "Null Object"
Используйте Files, чтобы в конструкторе класса Solution правильно инициализировать поле fileData объектом ConcreteFileData.
Если возникли какие-то проблемы со чтением файла по пути pathToFile, то инициализируйте поле объектом NullFileData.
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        try {
            Path file = Paths.get(pathToFile);
            boolean hidden = Files.isHidden(file);
            boolean executable = Files.isExecutable(file);
            boolean directory = Files.isDirectory(file);
            boolean writable = Files.isWritable(file);
            fileData = new ConcreteFileData(hidden, executable, directory, writable);
        } catch (Exception e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }

    public static void main(String[] args) {
        Solution solution = new Solution("f:/install.exe");
        System.out.println(solution.fileData.isHidden());
        System.out.println(solution.fileData.isExecutable());
        System.out.println(solution.fileData.isDirectory());
        System.out.println(solution.fileData.isWritable());
    }
}
