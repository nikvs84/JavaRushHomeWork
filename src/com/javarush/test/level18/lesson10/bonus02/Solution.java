package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        // args = new String[] {"-c", "asdaskjфыдфлдыдфлыо", "121", "12"};
        String productName = padRight(args[1], 30);
        String price = padRight(args[2], 8);
        String quantity = padRight(args[3], 4);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        String id = padRight(String.valueOf(getMaxId(fileName) + 1), 8);

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
        fileWriter.write(id + productName + price + quantity);
        fileWriter.newLine();
        fileWriter.close();
    }

    private static String padRight(String input, int length) {
        if (input.length() < length) {
            return String.format("%1$-" + length + "s", input);
        } else {
            return input.substring(0, length);
        }
    }

    private static long getMaxId(String fileName) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        long maxId = 0;
        long currId;
        String line;
        while ((line = fileReader.readLine()) != null) {
            currId = Integer.parseInt(line.substring(0, 8).trim());
            if (currId > maxId) {
                maxId = currId;
            }
        }
        fileReader.close();
        return maxId;
    }
/*    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
//        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");

        String recId = "";

//        System.out.println(randomAccessFile.length());

//        System.out.println(maxId);
//        randomAccessFile.seek(randomAccessFile.length());
        String newRec;
        newRec = makeRec((getMaxId(reader) + 1), args);
        System.out.println(newRec + " ------ " + newRec.length());
//        System.out.println(fileName);
//        System.out.println(newRec.getBytes().length);
//        randomAccessFile.write(newRec.getBytes());

        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.newLine();
        writer.write(newRec);
        writer.flush();
        writer.close();
//        randomAccessFile.close();
    }

    public static String convert(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append((char) b);
        }
        return sb.toString().trim();
    }

    public static String appendSpace(String s, int length) {
        StringBuffer sb = new StringBuffer();
        if (s.length() > length) {
            return s.substring(0, length);
        } else {
            sb.append(s);
            while (sb.length() < length)
                sb.append(" ");
        }
        return sb.toString();

    }

    public static String makeRec(int id, String[] ar) {
        StringBuffer sb = new StringBuffer();
        sb.append(appendSpace(("" + id), 8));
        sb.append(appendSpace(ar[1], 30));
        sb.append(appendSpace(ar[2], 8));
        sb.append(appendSpace(ar[3], 4));
        return sb.toString();
    }

    public static int getMaxId(BufferedReader reader) throws IOException {
        int maxId = 0;
        int id = 0;
        while (reader.ready()) {
            String s = reader.readLine().substring(0, 8);
            id = Integer.parseInt(s.trim());
            if (maxId < id)
                maxId = id;
        }
        return maxId;
    }

    public static int getMaxId(RandomAccessFile randomAccessFile) throws IOException {
        int position = 0;
        int maxId = 0;
        int id = 0;
        byte[] bytes = new byte[8];
        while (randomAccessFile.length() > position) {
            randomAccessFile.seek(position);
            randomAccessFile.read(bytes);
            position +=50;
            id = Integer.parseInt(convert(bytes));
            if (id > maxId)
                maxId = id;
        }
        return maxId;
    }*/
}
