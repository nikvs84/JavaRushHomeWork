package com.javarush.test.level34.lesson02.task03;

import java.util.Date;

/* Разложение на множители с помощью рекурсии
Разложить целое число n > 1 на простые множители.
Вывести в консоль через пробел все множители в порядке возрастания.
Написать рекуррентный метод для вычисления простых множителей.
Не создавайте статические переменные и поля класса.
Пример:
132
Вывод на консоль:
2 2 3 11
*/
public class Solution {
    public void recursion(int n) {
        if (n == 1 || n == 2 || n == 3) {
            System.out.print(n);
        } else {
            int range = (int) Math.sqrt(n);
            boolean isNotDiv = true;
            for (int i = 2; i <= range; i++) {
                if (n % i == 0) {
                    isNotDiv = false;
                    System.out.print(i + " ");
                    recursion(n / i);
                    break;
                }
            }
            if (isNotDiv)
                System.out.print(n);
        }
    }

/*
    public void recursion2(int n) {
        int a = 2;
        while (a <= n) {
            if ((n % a) == 0) {
                if (a != n) {
                    System.out.print(a + " ");
                    recursion(n / a);
                } else {
                    System.out.print(a);
                }
                return;
            }
            a++;
        }
    }
*/

    public static void main(String[] args) {
        Solution solution = new Solution();
        long start1 = new Date().getTime();
        for (int i = 132; i <= 132; i++) {
            System.out.print(i + " = ");
            solution.recursion(i);
            System.out.println();
        }
        long stop1 = new Date().getTime();
        System.out.println("Execution time: " + (stop1 - start1));

    }
}
