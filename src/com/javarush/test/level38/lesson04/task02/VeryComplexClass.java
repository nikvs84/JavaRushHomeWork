package com.javarush.test.level38.lesson04.task02;

/* Непроверяемые исключения (unchecked exception)
Напиши реализацию метода methodThrowsClassCastException(). Он должен
всегда кидать непроверяемое исключение ClassCastException.

Напиши реализацию метода methodThrowsNullPointerException(). Он должен
всегда кидать непроверяемое исключение NullPointerException.

Кинуть исключение (throw) явно нельзя.
*/

public class VeryComplexClass {
    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.methodThrowsClassCastException();
        veryComplexClass.methodThrowsNullPointerException();
    }
    public void methodThrowsClassCastException() {
        //напишите тут ваш код
        int i = (int) new Object();
    }

    public void methodThrowsNullPointerException() {
        //напишите тут ваш код
        String s = null;
        System.out.println(s.length());
    }
}
