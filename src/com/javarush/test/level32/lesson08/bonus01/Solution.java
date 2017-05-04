package com.javarush.test.level32.lesson08.bonus01;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Дженерики для создания прокси-объекта
В классе Solution создайте публичный метод getProxy
1) Метод getProxy должен возвращать прокси для любого интерфейса, который наследуется от Item
2) getProxy должен иметь два параметра. Первый - класс возвращаемого типа, второй - классы дополнительных интерфейсов.
3) Используйте ItemInvocationHandler для создания прокси
Метод main не участвует в тестировании
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    public <X extends Item> X getProxy(Class item, Class<?> ... other) {
        ItemInvocationHandler handler = new ItemInvocationHandler();
        ClassLoader loader = item.getClassLoader();
        Class<?>[] interfaces = new Class[other.length + 1];
        interfaces[0] = item;
        List<Class<?>> list = new ArrayList<>();
        list.addAll(Arrays.asList(other));
        for (int i = 0; i < list.size(); i++) {
            interfaces[i + 1] = list.get(i);
        }

        return (X) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
