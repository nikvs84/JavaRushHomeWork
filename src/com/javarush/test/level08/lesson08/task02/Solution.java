package com.javarush.test.level08.lesson08.task02;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/* Удалить все числа больше 10
Создать множество чисел(Set<Integer>), занести туда 20 различных чисел.
Удалить из множества все числа больше 10.
*/

public class Solution
{
    public static HashSet<Integer> createSet() throws IOException {
        //напишите тут ваш код
        HashSet<Integer> set = new HashSet<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 20; i++) {
            set.add(/*Integer.parseInt(reader.readLine())*/i);
        }
        return set;
    }

    public static HashSet<Integer> removeAllNumbersMoreThan10(HashSet<Integer> set)
    {
        //напишите тут ваш код
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            int n = iterator.next();
            if (n>10)
                iterator.remove();
        }
        return set;
    }
}
