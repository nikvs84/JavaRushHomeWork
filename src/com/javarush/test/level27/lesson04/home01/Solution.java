package com.javarush.test.level27.lesson04.home01;

/* Модификаторы и дедлоки
Расставьте модификаторы так, чтобы при работе с этим кодом появился дедлок
*/
public class Solution {
    public synchronized Object getData() {
        return new ThreadDeadlock().getData();
    }

    public static void main(String[] args) {
        Solution solution1 = new Solution();
        Solution solution2 = new Solution();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                solution1.getData();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                solution2.getData();
            }
        };
        thread1.start();
        thread2.start();
    }
}
