package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
//        int[] ar = searchCoord(crossword, 3, 2, "pu");
//        System.out.println(ar[0] + " " + ar[1]);
        List<Word> list = detectAllWords(crossword, "home", "same", "vo", "gr");
        System.out.println(list);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();
        int[][] searchDirections = new int[][] {
                {0, 1},
                {1, 1},
                {1, 0},
                {1, -1},
                {0, -1},
                {-1, -1},
                {-1, 0},
                {-1, 1},
        };
        for (String word : words) nextWord:{
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[i].length; j++) {
                    if (word.charAt(0) == crossword[i][j])
                        for (int directions = 0; directions < searchDirections.length; directions++) {
                            int     tmp_i = i,
                                    tmp_j = j,
                                    wordPos = 1;
                            while (wordPos < word.length()) {
                                tmp_i += searchDirections[directions][0];
                                tmp_j += searchDirections[directions][1];
                                if (tmp_i < 0 || tmp_i >= crossword.length || tmp_j < 0 || tmp_j >= crossword[tmp_i].length)
                                    break;
                                if (word.charAt(wordPos) != crossword[tmp_i][tmp_j])
                                    break;
                                else if (wordPos == word.length() - 1) {
                                    Word tWord = new Word(word);
                                    tWord.setStartPoint(j, i);
                                    tWord.setEndPoint(tmp_j, tmp_i);
                                    wordList.add(tWord);
                                    break nextWord;
                                }
                                wordPos++;
                            }
                        }
                }
            }
        }
        return wordList;
/*
        List<Word> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            wordFinder(list, crossword, words[i]);
        }
        return list;
*/
    }

    public static void wordFinder(List<Word> list, int[][] crossword, String word) {
        if ((word == null) || (word.isEmpty()))
            return;
        if (crossword == null)
            return;
        char c = word.charAt(0);
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length; j++) {
                if (c == (char) crossword[i][j]) {
                    searchCoord(list, crossword, i, j, word);
                }
            }
        }
    }

    public static void searchCoord(List<Word> list, int[][] crossword, int x, int y, String word) {
//        boolean b = false;
        int[] coord = {-1, -1};
        int length = word.length();
        //Если слово состоит из одной буквы
        if (length == 1) {
            coord[0] = x;
            coord[1] = y;
            putInList(list, word, x, y, coord[0], coord[1]);
            return;
        }
        //Если слово из нескольких букв, то проверяем, куда оно может быть направлено и сверяем буквы
        boolean b = false;
        int i, j, k;
        int xLength = crossword.length;
        int yLength = crossword[x].length;
        k = 1;
        //Берем вторую букву слова
        char cw;
//        cw = word.charAt(k);
        //Сначала идем слева направо
        i = x;
        j = y + 1;
        char cc;
        while ((k < length) && (j < yLength)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
//            i++;
            j++;
        }
        if (b) {
            coord[0] = i;
            coord[1] = --j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }
        //Идем налево
        b = false;
        k = 1;
        i = x;
        j = y - 1;
        while ((k < length) && (j >= 0)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
//            i++;
            j--;
        }
        if (b) {
            coord[0] = i;
//            if (j > 0)
            coord[1] = ++j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }
        //Идем вверх
        b = false;
        k = 1;
        i = x - 1;
        j = y;
        while ((k < length) && (i >= 0) && (i < xLength)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i--;
//            j--;
        }
        if (b) {
            coord[0] = ++i;
//            if (j > 0)
            coord[1] = j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }

        //Идем вниз
        b = false;
        k = 1;
        i = x + 1;
        j = y;
        while ((k < length) && (i < xLength)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i++;
//            j--;
        }
        if (b) {
            coord[0] = --i;
//            if (j > 0)
            coord[1] = j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }

        //Идем влево вверх!
        b = false;
        k = 1;
        i = x - 1;
        j = y - 1;
        while ((k < length) && (i >= 0) && (j >= 0)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i--;
            j--;
        }
        if (b) {
            coord[0] = ++i;
            coord[1] = ++j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }


        //Идем вправо вверх!
        b = false;
        k = 1;
        i = x - 1;
        j = y + 1;
        while ((k < length) && (i >= 0) && (j < yLength)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i--;
            j++;
        }
        if (b) {
            coord[0] = ++i;
            coord[1] = --j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }


        //Идем вправо вниз!
        b = false;
        k = 1;
        i = x + 1;
        j = y + 1;
        while ((k < length) && (i < xLength) && (j < yLength)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i++;
            j++;
        }
        if (b) {
            coord[0] = --i;
            coord[1] = --j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }


        //Идем влево вниз!
        b = false;
        k = 1;
        i = x + 1;
        j = y - 1;
        while ((k < length) && (i < xLength) && (j >= 0)) {
            cw = word.charAt(k);
            cc = (char) crossword[i][j];
            b = (cw == cc);
            if (!b)
                break;
            k++;
            i++;
            j--;
        }
        if (b) {
            coord[0] = --i;
            coord[1] = ++j;
            putInList(list, word, x, y, coord[0], coord[1]);
        }

    }

    public static void putInList(List<Word> list, String s, int startX, int startY, int endX, int endY) {
        Word word = new Word(s);
        word.setStartPoint(startX, startY);
        word.setEndPoint(endX, endY);
        list.add(word);
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
