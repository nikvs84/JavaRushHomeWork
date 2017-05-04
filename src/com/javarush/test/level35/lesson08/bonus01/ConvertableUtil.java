package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <Key, Val> Map<Key, Val> convert(List<? extends Convertable<Key>> list) {
        Map<Key, Val> result = new HashMap();
        for (Convertable<Key> item: list) {
            Key key = item.getKey();
            Val val = (Val) item;
            result.put(key, val);
        }
        return result;
    }

    private static String getItemValue(Convertable item) {
        String result = "";
        String toStr = item.toString();
        int startPos = toStr.indexOf("name='") + "name='".length();
        int stopPos = toStr.indexOf('\'', startPos);
        result = toStr.substring(startPos, stopPos);
        return result;
    }
}
