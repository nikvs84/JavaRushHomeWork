package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 14.10.2016.
 */
public class CurrencyManipulatorFactory {
    static Map<String, CurrencyManipulator> manipulatorMap = new HashMap<>();
    private CurrencyManipulatorFactory() {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (!manipulatorMap.containsKey(currencyCode)) {
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
            manipulatorMap.put(currencyCode, currencyManipulator);
            return currencyManipulator;
        } else return manipulatorMap.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return manipulatorMap.values();
    }

}
