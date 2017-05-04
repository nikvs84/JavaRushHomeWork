package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Ai", "Be");
        map.put("Ci", "De");
        map.put("Ei", "Fe");
        map.put("Gi", "He");
        map.put("Ii", "Je");
        map.put("Ki", "Le");
        map.put("Mi", "Ne");
        map.put("Oi", "Pe");
        map.put("Ri", "Se");
        map.put("Ti", "Ue");
        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int count = 0;
        for (String  s: map.values()) {
            if (name.equals(s))
                count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        //напишите тут ваш код
        int count = 0;
        for (String s: map.keySet()) {
            if (lastName.equals(s))
                count++;
        }
//        if (map.keySet().contains(lastName))
//            count = 1;
        return count;

    }

//    public static void main(String[] args) {
//        Map map = createMap();
//        System.out.println(getCountTheSameFirstName(map, "4"));
//        System.out.println(getCountTheSameLastName(map, "1"));
//    }
}
