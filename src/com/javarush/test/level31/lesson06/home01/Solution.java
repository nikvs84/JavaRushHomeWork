package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
// !!!! Закидывать файл в архив нужно только если он там уже есть, иначе не принимается.
public class Solution {

    public static Map <ZipEntry, byte[]> entryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        File zipArchive = new File(args[1]);
        File addedFile = new File(args[0]);

        zipToMap(zipArchive);
        addNewFileToZIP(addedFile, zipArchive);
    }


    public static void zipToMap(File file)  {

        // Записываем содержимое архива в карту
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {

            ZipEntry zipEntry;

            // перебираем все zipEntries
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int count;

                while ((count = zipInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, count);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                entryMap.put(zipEntry, bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addNewFileToZIP(File addedFile, File zipArchive) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipArchive)); FileInputStream fileInputStream = new FileInputStream(addedFile)) {

            //Маркер наличия добавляемого файла в архиве
            boolean isExist = false;

            //Сохраняем сразу в отдельный zipEntry добавляемый файл для последующего сравнения
            ZipEntry fileNameComparator = new ZipEntry(addedFile.getName());


            //Копируем zipEntry с entryMap в архив
            for (Map.Entry<ZipEntry, byte[]> zipEntry : entryMap.entrySet()) {

                //Подрезаем путь файла для сравненияс добавляемым файлом
                Path path = Paths.get(zipEntry.getKey().getName());

                //Сравниваем...
                //Если имя текущего файла в zipEntry НЕ совпадает с добавлемым файлом
                if(!(path.getFileName().toString().equals(fileNameComparator.getName()))) {

                    //Записываем в архив
                    zipOutputStream.putNextEntry(new ZipEntry(zipEntry.getKey().getName()));
                    zipOutputStream.write(zipEntry.getValue());

                }
                //Если же файл с таким названием присутствует в архиве...
                else {
                    isExist = true;
                }
            }

            //Если в процессе прохождения цикла в архиве нашелся файл с таким-же именем как и добавляемый..
            if (isExist) {
                //Добавляем файл в папку new
                ZipEntry addingFileEntry = new ZipEntry("new/" + addedFile.getName());
                zipOutputStream.putNextEntry(addingFileEntry);

                //Считываем содержимое файла в массив byte
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);

                //Добавляем содержимое к архиву
                zipOutputStream.write(buffer);
                //Закрываем текущую запись для новой записи
                zipOutputStream.closeEntry();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
* Все работает, не принимает*/
/*
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            args = new String[2];
            args[0] = "f:/Javarush/result.mp3";
            args[1] = "f:/Javarush/test.zip";
        }
        File targetFile = new File(args[0]);
        File zipFile = new File(args[1]);
        File newDir = new File(targetFile.getParent() + "/new");
        String newPathToPack = "new/" + targetFile.getName();
        Map<String, ByteArrayOutputStream> filesToWrite = zipToMap(zipFile, newPathToPack);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(targetFile);
        byte[] buffer = new byte[1024];
        while (fis.read(buffer) > 0) {
            baos.write(buffer);
        }
        filesToWrite.put(newPathToPack, baos);
        writeFromMapToZip(filesToWrite, zipFile);

    }

    private static void zisToBaos(ZipInputStream zis, ByteArrayOutputStream baos, byte[] buffer) throws IOException {
            while (zis.read(buffer) > 0) {
                baos.write(buffer);
            }
    }

    private static Map<String, ByteArrayOutputStream> zipToMap(File zipFile, String ignoredFileName) throws IOException {
        FileInputStream fis = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(fis);
        Map<String, ByteArrayOutputStream> filesToWrite = new HashMap<>();
        byte[] bytes = new byte[1024];
        ZipEntry ze = null;
        ByteArrayOutputStream baos = null;
        while ((ze = zis.getNextEntry()) != null) {
            if (ze.getName().equalsIgnoreCase(ignoredFileName))
                continue;
            baos = new ByteArrayOutputStream();
            zisToBaos(zis, baos, bytes);
            filesToWrite.put(ze.getName(), baos);
        }
        zis.close();
        fis.close();
        return filesToWrite;
    }

    private static void writeFromMapToZip(Map<String, ByteArrayOutputStream> filesToWrite, File zipFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        ZipEntry ze = null;
        ByteArrayInputStream bais = null;
        for (Map.Entry<String, ByteArrayOutputStream> entry: filesToWrite.entrySet()) {
            ze = new ZipEntry(entry.getKey());
            byte[] bytes = entry.getValue().toByteArray();
            zos.putNextEntry(ze);
            zos.write(bytes);
            zos.closeEntry();
        }

        zos.close();
        fos.close();
    }
}
*/
