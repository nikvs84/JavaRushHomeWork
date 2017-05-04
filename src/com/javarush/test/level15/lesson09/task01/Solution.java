package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    public static void main(String[] args) {
        System.out.println(labels);
    }

    static {
        labels.put(1d, "a");
        labels.put(2d, "b");
        labels.put(3d, "c");
        labels.put(4d, "d");
        labels.put(5d, "e");
    }
}
