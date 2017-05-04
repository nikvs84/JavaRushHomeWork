package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Date;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static void main(String[] args) {
//        getNumbers(10000000);
        Date time = new Date();
//        int[] ar = getNumbers(100000000);
        System.out.println(isMatch(987654));
        Date time2 = new Date();
        long deltaTime = (long) ((time2.getTime() - time.getTime()) / 1000);
/*
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);
        }
*/
        System.out.println(deltaTime);
//        System.out.println(getDigitArray(7));
//        System.out.println(isMatch(548834));
//        System.out.println(pow(5, 6));
    }
    public static int[] getNumbers(int N) {
        int[] result = null;
        ArrayList<Integer> list = new ArrayList<>();
        N = N + 1;
        for (int i = 1; i < N; i++) {
            if (isMatch(i)) {
//                System.out.println(i + " = true");
                list.add(i);
                if ((i % 10) == 0) {
                    list.add(++i);
                }
            }
        }
        result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static int getExp(int N) {
        int exp = 0;
        while (N >= 10) {
            N = N / 10;
            exp++;
        }
        return exp;
    }

    public static int[] getDigitArray(int N) {
        int exp = getExp(N);
        int[] ints = new int[exp + 1];
        for (int i = 0; i < ints.length; i++) {
            int dec;
//            dec = (int) Math.pow(10, exp);
            dec = pow(10, exp - i);
            int ost = N % dec;
            ints[i] = (N - ost) / dec;
            N = ost;
//            System.out.println(ints[i]);
        }
        return ints;
    }

    public static int pow(int i, int exp) {
        if (exp <= 0)
            return 1;
        int temp = i;
        for (int j = 1; j < exp; j++) {
            i = i * temp;
        }
        return i;
    }

    public static boolean isMatch(int number) {
        boolean isMatch = false;
        if ((number < 10) && (number > 0))
            return true;
        int[] digits = getDigitArray(number);
        int exp = digits.length;
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            int t = pow(digits[i], exp);
            sum += t;
            if (sum > number)
                return false;
        }
        isMatch = (sum == number);
        return isMatch;
    }

}
