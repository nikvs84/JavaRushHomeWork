package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        for (int i = 0; i < solutions.length; i++) {
            Solution solution = new Solution();
            for (int j = 0; j < solution.innerClasses.length; j++) {
                solution.innerClasses[j] = solution.new InnerClass();
            }
            solutions[i] = solution;
        }
        return solutions;
    }
}
