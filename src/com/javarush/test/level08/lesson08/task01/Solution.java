package com.javarush.test.level08.lesson08.task01;

import java.io.IOException;
import java.util.HashSet;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet() throws IOException {
        //напишите тут ваш код
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
//        for (int i = 0; i < 20; i++) {
//            String s = reader.readLine();
//            if ("Л".equals(s.charAt(0))){
//                set.add(s);
//                i++;
//            }
//        }
        set.add("Л1");
        set.add("Л2");
        set.add("Л3");
        set.add("Л4");
        set.add("Л5");
        set.add("Л6");
        set.add("Л7");
        set.add("Л8");
        set.add("Л9");
        set.add("Л10");
        set.add("Л11");
        set.add("Л12");
        set.add("Л13");
        set.add("Л14");
        set.add("Л15");
        set.add("Л16");
        set.add("Л17");
        set.add("Л18");
        set.add("Л19");
        set.add("Л20");
        return set;
    }
}
