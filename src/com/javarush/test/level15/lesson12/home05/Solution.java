package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution() {}
    public Solution(int i) {}
    public Solution(short i) {}

    Solution(double i){}
    Solution(float i){}
    Solution(long i){}

    protected Solution(String s) {}
    protected Solution(String s, int i) {}
    protected Solution(String s, byte b) {}

    private Solution(boolean b) {}
    private Solution(boolean b, boolean bb) {}
    private Solution(boolean b, boolean bb, boolean bbb) {}

}

