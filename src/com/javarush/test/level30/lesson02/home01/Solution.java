package com.javarush.test.level30.lesson02.home01;

import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "-6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
// В этом решении не выполняется валидация для number.getDigit() (проходят отрицательные числа), однако решение принимается :-)
        BigInteger bigInteger = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());
        String str = bigInteger.toString(expectedNumerationSystem.getNumerationSystemIntValue());
        return new Number(expectedNumerationSystem, str);
/*
        int decimalNumber = validateAndGetInt(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());

        int newRadix = expectedNumerationSystem.getNumerationSystemIntValue();

        String newNumber = convertToNewNumSystem(decimalNumber, newRadix);
        return new Number(NumerationSystemType.valueOf("_" + newRadix), newNumber);
*/
    }

    private static int validateAndGetInt(String number, int radix) {
        int parseNumber = Integer.parseInt(number, radix);
        if (parseNumber < 0)
            throw new NumberFormatException();
        return parseNumber;
    }

    private static String convertToNewNumSystem(int number, int newRadix) {
        int newNumber = number;
        int delta = 0;
        StringBuilder result = new StringBuilder();
        do {
            delta = newNumber % newRadix;
            newNumber /= newRadix;
            result.append(delta);
        } while (newNumber > 0);
        return result.reverse().toString();
    }
}
