package com.javarush.test.level20.lesson10.bonus02;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int startI = 0, startJ = 0, endI = 0, endJ = 0;
        byte count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                //Ищем координаты верхней левой вершины прямоугольника
                if ((j < a.length) && (i < a.length) && (a[i][j] == 1)) {
                    count++;
//                    System.out.println("count = " + count);
                    startI = i;
                    startJ = j;
                    //Координаты верхней левой вершины найдены

                    //Ищем координаты нижней правой вершины
//                    System.out.println("startI = " + startI +  " startJ = " + startJ);

                    while ((j < a.length) && (i < a.length) && (a[i][j] == 1)) {
                        j++;
                    }
                    endJ = j - 1;
                    for (int ii = startI; ii < a.length; ii++) {
                        if (a[ii][endJ] == 1) {
                            endI = ii;
                        } else break;
                    }
                    //Координаты нижней правой вершины найдены

                    //Отмечаем найденный прямоугольник значением "-1", чтобы исключить его из поиска
//                    System.out.println("endI = " + endI +  " endJ = " + endJ);
                    for (int k = startI; k < endI + 1; k++) {
                        for (int l = startJ; l < endJ + 1; l++) {
                            a[k][l] = -1;
                        }
                    }
                }
//                printMatrix(a);
//                System.out.println();
            }
        }
        return count;
    }

    public static void printMatrix(byte[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
