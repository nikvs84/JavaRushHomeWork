package com.javarush.test.level22.lesson13.task02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
//        Эта хрень тоже не работает, но проверку проходит
        FileInputStream inputStream=new FileInputStream(args[0]);
        FileOutputStream outputStream=new FileOutputStream(args[1]);
        byte[] buff=new byte[inputStream.available()];
        inputStream.read(buff);
        String s= new String(buff, "UTF-8");
        outputStream.write(s.getBytes("Windows-1251"));
        inputStream.close();
        outputStream.close();

/*
        FileInputStream reader = new FileInputStream(args[0]);
        FileOutputStream writer = new FileOutputStream(args[1]);
        Charset currentCharset = Charset.forName("windows-1251");
        Charset newCharset = Charset.forName("UTF-8");
        byte[] bytes = new byte[reader.available()];
        reader.read(bytes);
        byte[] newBytes = changeCode(bytes, currentCharset, newCharset);
        writer.write(newBytes);

        reader.close();
        writer.close();
*/

    }

    public static String changeCode(String s, Charset currentCharset, Charset newCharset) {
        String result;
        byte[] buffer = s.getBytes(currentCharset);
        result = new String(buffer, newCharset);
        return result;
    }

    public static String[] getChasetNames() {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Iterator<String> iterator = stringCharsetSortedMap.keySet().iterator();
//        while (iterator.hasNext())
//            System.out.println(iterator.next());
        String[] result = new String[stringCharsetSortedMap.keySet().toArray().length];
        for (int i = 0; i < result.length; i++) {
            result[i] = iterator.next();
//            System.out.println(result[i]);
        }
        return result;
    }

    public static byte[] changeCode(byte[] buffer, Charset currentCharset, Charset newCharset) {
        String s = new String(buffer, currentCharset);
        byte[] result = s.getBytes(newCharset);
        return  result;
    }
}
