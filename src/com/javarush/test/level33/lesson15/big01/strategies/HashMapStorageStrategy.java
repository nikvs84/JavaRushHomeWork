package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;

/**
 * Created by Admin on 14.11.2016.
 */
public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        for (Long l: data.keySet()) {
            if (data.get(l).equals(value)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
