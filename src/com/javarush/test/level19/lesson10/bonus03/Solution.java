package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        ArrayList<StringBuffer> tagList = new ArrayList<>();
        reader.close();
        reader = new BufferedReader(new FileReader(fileName));
        StringBuffer stringBuffer = new StringBuffer();
        while (reader.ready()) {
            stringBuffer.append(reader.readLine());
        }
        String content = stringBuffer.toString();
        reader.close();

        ArrayList<String> finalList = contentToList2(content, args[0]);
        for (int i = 0; i <finalList.size() ; i++) {
            System.out.println(finalList.get(i));
        }
    }

    public static class ContentList {
        public String tag;
        public ArrayList<ContentList> subtags;
        public ContentList() {
        }
    }

    public static ArrayList<StringBuffer> contentToList(String content, String tag) {
        ArrayList<StringBuffer> tagList = new ArrayList<>();
        String tagOpen = "<" + tag;
        String tagClose = "</" + tag + ">";

        int tagCount = 0;
        int currentIndex = 0;
        StringBuffer stringBuffer = new StringBuffer();
        boolean isTagCountDown = false;
        for (int i = 0; i < content.length(); i++) {
            if (isOpenTag(tagOpen, content, i)) {
                tagCount++;
                if (tagCount > tagList.size())
                    tagList.add(new StringBuffer());
            }
            else
            if (isCloseTag(tagClose, content, i)) {

                for (int j = 0; j < tagCount; j++) {
                    tagList.get(j).append(content.charAt(i));
                }
                tagCount--;
                isTagCountDown = true;
            }
            if (!isTagCountDown)
                for (int j = 0; j < tagCount; j++) {
                    tagList.get(j).append(content.charAt(i));
                }
            isTagCountDown = false;
        }
/*
        for (int i = 0; i < tagList.size(); i++) {
            System.out.println(tagList.get(i).toString());
        }
*//*

        ArrayList<String[]> tl = new ArrayList<>();
        for (int i = 0; i < tagList.size(); i++) {
            String s = tagList.get(i).toString();
            tl.add(splitTag(s, args[0]));
        }
        for (int i = 0; i < tl.size(); i++) {
            for (int j = 0; j < tl.get(i).length; j++) {
                System.out.println(tl.get(i)[j]);
            }
        }
*/
        return tagList;
    }

    public static ArrayList<String> contentToList2(String content, String tag) {
        ArrayList<StringBuffer> tagList = new ArrayList<>();
        ArrayList<ContentList> contentLists = new ArrayList<>();
        ArrayList<String> finalList = new ArrayList<>();
        String tagOpen = "<" + tag;
        String tagClose = "</" + tag + ">";

        int tagCount = 0;
        int currentIndex = 0;
        StringBuffer stringBuffer = new StringBuffer();
        boolean isTagCountDown = false;
        for (int i = 0; i < content.length(); i++) {
            if (isOpenTag(tagOpen, content, i)) {
                tagCount++;
                if (tagCount > tagList.size())
                    tagList.add(new StringBuffer());
            }
            else
            if (isCloseTag(tagClose, content, i)) {
                for (int j = 0; j < tagCount; j++) {
                    tagList.get(j).append(content.charAt(i));
                }
                tagCount--;


                if (tagCount == 0) {
                    for (int j = 0; j < tagList.size(); j++) {
                        finalList.add(tagList.get(j).toString());
                    }
                    tagList = new ArrayList<>();
                }
                isTagCountDown = true;
            }
            if (!isTagCountDown)
                for (int j = 0; j < tagCount; j++) {
                    tagList.get(j).append(content.charAt(i));
                }
            isTagCountDown = false;
        }
        return finalList;
    }


    public static boolean isOpenTag(String tag, String content, int index) {
        boolean b = false;
        int len = tag.length();
        if ((index + len - 1) >= content.length())
            return false;
        else {
            String s = content.substring(index, index + len);
            b = (tag.equals(s));
        }
        return b;
    }

    public static boolean isCloseTag(String tag, String content, int index) {
        boolean b = false;
        int len = tag.length();
        if ((index - len + 1) < 0)
            return false;
        else {
            String s = content.substring(index - len + 1, index + 1);
            b = (tag.equals(s));
        }
        return b;
    }

    public static String[] splitTag(String s, String tag) {
        String tg = "</" + tag + "><" + tag + ">";
        String[] ss = s.split(tg);
        if (ss.length == 1)
            return ss;
        ss[0] = ss[0] + "</"+ tag + ">";
        for (int i = 1; i < ss.length - 1; i++) {
            ss[i] = "<" + tag + ">" + ss[i] + "</"+ tag + ">";
        }
        ss[ss.length - 1] ="<" + tag + ">" +  ss[ss.length - 1];
        return ss;
    }

}
