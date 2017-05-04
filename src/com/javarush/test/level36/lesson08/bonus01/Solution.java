package com.javarush.test.level36.lesson08.bonus01;

/* Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберитесь в коде и исправьте ошибки.
Метод main не участвует в тестировании.
Все модификатры правильные.
Имена переменных и методов не изменяйте.
*/
public class Solution {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(1);
        for (int i = 0; i < 20; i++) {
            tree.insert(i);
        }
        for (int i = 0; i < 20; i++) {
            RedBlackTree.Node node = tree.current;
        }
    }
}
