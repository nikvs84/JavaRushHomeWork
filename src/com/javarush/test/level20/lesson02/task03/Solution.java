package com.javarush.test.level20.lesson02.task03;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception{
        Solution sol = new Solution();
        sol.fillInPropertiesMap();
        FileOutputStream fOS = new FileOutputStream("d:\\b.properties");
        sol.save(fOS);
        fOS.close();
    }

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        String fileName;
        Scanner sc = new Scanner(System.in);
        fileName = sc.nextLine();
        sc.close();

        try {
            FileInputStream fIS= new FileInputStream(fileName);
            this.load(fIS);
            fIS.close();
        } catch (IOException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties tmp_properties = new Properties();
        for (Map.Entry<String, String> stringStringEntry : properties.entrySet()) {
            tmp_properties.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        tmp_properties.save(outputStream, null);
        outputStream.flush();
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties tmp_properties = new Properties();
        tmp_properties.load(inputStream);
        for (Map.Entry<Object, Object> objectObjectEntry : tmp_properties.entrySet()) {
            properties.put((String)objectObjectEntry.getKey(), (String)objectObjectEntry.getValue());
        }
    }
}

/*
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Solution.fillInPropertiesMap();
        FileOutputStream outputStream = new FileOutputStream("d:\\b.properties");
        save(outputStream);
//        String[] s = new String[6];
//        s[0] = "\\\\\\!abba";
//        s[1] = "\\\\#hh\\ m";
//        s[2] = "\\#5hu";
//        s[3] = "hgjgv!kjhk";
//        s[4] = "jyhb\\#jnk   y";
//        s[5] = "         hjyv\\           lkjnl   jn\\!";
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(conditionString(s[i]));
//            String ss;
//            ss = stringWithoutComments(s[i]);
//            System.out.println(ss);
//            ss = trimSplitter(ss, " ");
//            System.out.println(ss);
//            ss = deleteSplitter(ss, "\\", " ");
//            System.out.println(ss);
//            ss = trimBackSlash(ss);
//            System.out.println(ss);
//            ss = stringWithoutEkran(ss);
//            System.out.println(ss);
//            ss = trimBackSlash(s[i]);
//            System.out.println(ss);
//            ss = s[i].replaceAll("^\\s*", "");
//            ss = s[i].replaceAll("([^\\x5c]!.*)", "");
//            System.out.println(ss);
//        }
    }

    public static void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        InputStream inputStream = new FileInputStream(fileName);
        load(inputStream);
    }

    public static void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter writer = new PrintWriter(outputStream);
        Iterator<Map.Entry<String, String>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String > pair = iterator.next();
            String chars = ": =#!";
            String rec = stringToProperty(pair.getKey(), chars) + " = " + stringToProperty(pair.getValue(), chars);
            writer.println(rec);
        }
        writer.close();
    }

    public static void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (reader.ready()) {
            String rec = reader.readLine();
            if (rec.startsWith("!") || rec.startsWith("#"))
                continue;
            if (rec.endsWith("\\")) {
                String rec2 = reader.readLine();
                rec = rec.substring(0, rec.length() - 1);
                rec = rec + rec2;
            }
            if ((rec == null) || (rec.isEmpty()))
                continue;
            String[] prop = formatRec(rec, " =:");
            if ((prop[0] == null) || (prop[0].isEmpty()))
                continue;
            properties.put(prop[0], prop[1]);
        }
        reader.close();
    }

    public static String[] formatRec(String string, String splitter) {
        while (string.startsWith(splitter)) {
            string = string.substring(1);
        }
        String result[] = new String[2];
        int pos = string.length() - 1;
        for (int i = 0; i < string.length(); i++) {
            if (isSplitter(string.charAt(i), splitter)) {
                pos = i;
                if ((pos > 0) && !isEkran(string.substring(0, pos)))
                break;
            }
        }

            result[0] = string.substring(0, pos);
            result[1] = string.substring((pos + 1));

        String s;
        s = conditionString(result[0]);
        result[0] = s;
        s = conditionString(result[1]);
        result[1] = s;
        return result;
    }

//    public static String stringWithoutEkran(String s) {
//        String result = "";
//        if (s == null || s.isEmpty())
//            return result;
//        s = s.replaceAll("^\\s*", "");
////        s = s.replaceAll("\\s+", " ");
//        char[] chars = new char[s.length()];
//        int len = 1;
//        if (s.charAt(0) != '\\')
//            chars[0] = s.charAt(0);
//        for (int i = 1; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c != '\\') {
//                if (c != ' ') {
//                    chars[len] = c;
//                    len++;
//                }
//                else {
//                    char cc = s.charAt(i - 1);
//                    if (cc == '\\') {
//                        chars[len] = c;
//                        len++;
//                    }
//
//                }
//
//            }
//        }
//        result = new String(chars, 0, len);
////        s = s.replaceAll("([^\\x5c]\\x5c)|(^\\x5c)", "");
////        result = s;
//        return result;
//    }

    public static String stringWithoutComments(String s) {
        if (s == null || s.isEmpty())
            return "";
        if (s.startsWith("!") || s.startsWith("#"))
            return "";
        String result = "";
        int len = 1;
        char[] chars = new char[s.length()];
        chars[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '!' || c == '#') {
                char cc = s.charAt(i - 1);
                if (isComment(s.substring(0, i))) {
                    result = new String(chars, 0, len);
                    break;
                }
                chars[len] = c;
                len++;
            }
            else {
                chars[len] = c;
                len++;
            }
        }
        result = new String(chars, 0, len);
        return result;
    }

    public static String trimEkran(String s, String ekran) {
        String result = "";
        if (s == null || s.isEmpty())
            return result;
        char ekr = ekran.charAt(0);
        StringBuffer sb = new StringBuffer();
        int pos = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == ekr) {
                pos = i;
                char cc = s.charAt(++i);
                    sb.append(cc);
            } else
                sb.append(c);

        }
        if ((pos != s.length() - 2) && (!s.endsWith("\\")))
            sb.append(s.charAt(s.length() - 1));
        result = sb.toString();
        return result;
    }

    public static boolean isComment(String s) {
        int count = 0;
        int i = s.length() - 1;
        char c = s.charAt(i);
        while ((i >= 0) && (c == '\\')) {
            c = s.charAt(i);
            --i;
            if (c != '\\')
                break;
            count++;
        }
        return (count % 2 == 0);
    }

    public static boolean isEkran(String s) {
        return !(isComment(s));
    }

    public static String deleteSplitter(String s, String ekran, String splitter) {
        String result = "";
        if ((s == null) || (s.isEmpty()))
            return result;
        int pos = 1;
        StringBuffer sb = new StringBuffer();
        sb.append(s.charAt(0));
        while (pos < s.length()) {
            String ss = s.substring(pos, pos + splitter.length());
            if (ss.equals(splitter)) {
                String eString = s.substring(0, pos);
                if (isEkran(eString)) {
                    sb.append(s.charAt(pos++));
                } else pos++;
            }
            if (pos < s.length())
            sb.append(s.charAt(pos++));
        }
        result = sb.toString();
        return result;
    }

    public static String deleteAllSplitters(String s, String ekran, String splitter) {
        String result = "";
        for (int i = 0; i < splitter.length(); i++) {
            String ss = splitter.substring(i, i + 1);
            s = deleteSplitter(s, ekran, ss);
        }
        result = s;
        return result;
    }

    public static String trimSplitter(String s, String splitter) {
        String result = "";
        while (s.contains(splitter + splitter)) {
            s = s.replaceAll((splitter + splitter), splitter);
        }
        result = s;
        return result;
    }

    public static String trimAllSplitters(String s, String splitter) {
        String result = "";
        s = deleteStartSplitters(s, splitter);
        for (int i = 0; i < splitter.length(); i++) {
            s = trimSplitter(s, splitter.substring(i, i + 1));
        }
        result = s;
        return result;
    }

    public static boolean isSplitter(char c, String splitter) {
        if ((splitter == null) || (splitter.isEmpty()))
            return false;
        Character cc = c;
        return splitter.contains(cc.toString());
    }

    public static boolean isSplitter(String s, String splitter) {
        if ((splitter == null) || (splitter.isEmpty()))
            return false;
        return splitter.contains(s);
    }

    public static String deleteStartSplitters(String s, String splitter) {
        String ss = s.substring(0, 1);
        while ((s.length() > 1) && splitter.contains(ss)) {
            s = s.substring(1);
            ss = s.substring(0, 1);
        }
        if ((s.length() == 1) && splitter.contains(s))
            s = "";
        return s;
    }

    public static String conditionString(String s) {
        s = stringWithoutComments(s);
        s = trimAllSplitters(s, " =:");
        s = deleteAllSplitters(s, "\\", " =:");
        s = trimEkran(s, "\\");
        return s;
    }

    public static String stringToProperty(String s, String chars) {
        char ekran = '\\';
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (isSplitter(c, chars))
                sb.append(ekran);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String formatRecToString(String[] strings) {
            return strings[0] + ": = :" + strings[1];
    }
}
*/
