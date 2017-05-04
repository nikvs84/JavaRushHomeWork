package com.javarush.test.level25.lesson09.task03;

import java.util.Stack;

/* Живем своим умом
В классе Solution реализуйте интерфейс UncaughtExceptionHandler, который должен:
1. прервать нить, которая бросила исключение.
2. вывести в консоль стек исключений начиная с самого вложенного.
Пример исключения: new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
Пример вывода:
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    public static void main (String args[]) {
        Solution solution = new Solution();
        solution.uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        Stack<Throwable> stack = new Stack<>();
        stack.push(e);
        Throwable throwable = e.getCause();
        while (throwable != null) {
            stack.push(throwable);
            throwable = throwable.getCause();
        }
        while (!stack.isEmpty()){
            throwable = stack.pop();
            System.out.println(throwable.getClass().getName() + ": " + throwable.getMessage());
        }

    }
}
