package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            args = new String[6];
            args[0] = "result.mp3";
            args[1] = "f:/Javarush/test.zip.003";
            args[2] = "f:/Javarush/test.zip.001";
            args[3] = "f:/Javarush/test.zip.004";
            args[4] = "f:/Javarush/test.zip.002";
            args[5] = "f:/Javarush/test.zip.005";
        }

        String resultFileName = args[0];

        List<String> fileList = new ArrayList<>();

        fileList.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(fileList);

        List<FileInputStream> fileInputStreamList = new ArrayList<>();

        for (int i = 0; i < fileList.size(); i++) {

            try {
                fileInputStreamList.add(new FileInputStream(fileList.get(i)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(fileInputStreamList));

        ZipInputStream zipInStream = new ZipInputStream(sequenceInputStream);
        FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];

        while (zipInStream.getNextEntry() != null) {

            int count;
            while ((count = zipInStream.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }

        sequenceInputStream.close();
        zipInStream.close();
        fileOutStream.close();
    }
}
/*
* Обе версии не работали со стандарнтым Zip-архивом Windows 10, разбитым при помощи TotalCmd*/
/*
        byte[] buffer = new byte[1024 * 1024];
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(list);
        List<FileInputStream> inputStreamList = new ArrayList<>();
        for (String fileName : list) {
            fis = new FileInputStream(fileName);
            inputStreamList.add(fis);
*/
/*
            while (fis.read(buffer) > 0) {
                baos.write(buffer);
            }
*//*

        }
        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(inputStreamList));
        ZipInputStream zis = new ZipInputStream(sequenceInputStream);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(args[0]));

*/
/*
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ZipInputStream zis = new ZipInputStream(bais);

*//*

        zis.getNextEntry();
        int count;
        while ((count = zis.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer);
        }
//        bais.close();
        bufferedOutputStream.close();

        baos.close();
        fis.close();

        zis.close();
*/
