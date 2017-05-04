package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(getMediana(array));
        array = sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer mediana = getMediana(array);
        for (int i = 0; i < array.length - 1; i++) {
            Integer a = array[i];
            for (int j = i + 1; j < array.length; j++) {
                Integer b = array[j];
                int dist1 = dist(mediana, a);
                int dist2 = dist(mediana, b);
                if (dist1 > dist2) {
                    array[i] = b;
                    array[j] = a;
                    a = b;
                } else if (dist(mediana, a) == dist(mediana, b)) {
                    if (a > b) {
                        array[i] = b;
                        array[j] = a;
                        a = b;
                    }
                }
            }
        }
        return array;
    }

    public static Integer getMediana(Integer[] array) {
        Integer result = 0;
        Integer[] ar = new Integer[array.length];
        ar = Arrays.copyOf(array, array.length);
        Arrays.sort(ar);
        if (ar.length % 2 != 0)
            result = ar[(ar.length / 2)];
        else {
            int i = ar.length / 2 - 1;
            result = (ar[i] + ar[i + 1]) / 2;
        }
        return result;
    }

    public static int dist(Integer a, Integer b) {
        return Math.abs(a - b);
    }

}
