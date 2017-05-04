package com.javarush.test.level22.lesson13.task01;

import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {
    public static void main(String[] args) {
        String[] strings = getTokens("level22.lesson13.task01", ".");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
    public static String [] getTokens(String query, String delimiter) {
        String[] result;
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        result = new String[tokenizer.countTokens()];
        for (int i = 0; i < result.length; i++) {
            result[i] = tokenizer.nextToken();
        }
        return result;
    }
}
