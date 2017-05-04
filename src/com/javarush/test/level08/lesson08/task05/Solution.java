package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> map = new HashMap<>();
        map.put("Ai", "Bee");
        map.put("Ci", "Dee");
        map.put("Ei", "Bee");
        map.put("Gi", "Bee");
        map.put("Ii", "Jee");
        map.put("Ki", "Lee");
        map.put("Mi", "Nee");
        map.put("Oi", "Pee");
        map.put("Ri", "See");
        map.put("Ti", "Uee");
        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        HashMap<String, String> hm = new HashMap<>();
//        while (iterator.hasNext()){
//            HashMap.Entry<String, String> item = iterator.next();
//            hm.put(item.getValue(), item.getKey());
//        }
//        map.clear();
//        Iterator<HashMap.Entry<String, String>> iter = hm.entrySet().iterator();
//        while (iter.hasNext()){
//            HashMap.Entry<String, String> item = iter.next();
//            map.put(item.getValue(), item.getKey());
//        }
        HashMap<String, String> hm = new HashMap<>();
        for(Map.Entry<String, String> item: map.entrySet()){
            int count=0;
            for (Map.Entry<String, String> pair: map.entrySet()) {
                String name = pair.getValue();
                if (item.getValue().equals(name))
//                    removeItemFromMapByValue(map, name);
                count++;
            }
            if (count>1)
                hm.put(item.getKey(), item.getValue());
        }
        for (Map.Entry<String, String> item: hm.entrySet()) {
            String name = item.getValue();
            removeItemFromMapByValue(map, name);
        }
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> map = createMap();
        removeTheFirstNameDuplicates(map);
        for (HashMap.Entry pair: map.entrySet()) {
            System.out.println(pair);
        }
    }
}
