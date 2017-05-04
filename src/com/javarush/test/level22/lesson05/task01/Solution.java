package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution {

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {

        if (string == null || string.isEmpty()) throw new TooShortStringException();
        int firstSpaceIndex = string.indexOf(" ") + 1;
        char[] chars = string.toCharArray();
        int countSpace = 0;
        int lastSpaceIndex = 0; //
        for (int i = 0; i < string.length(); i++)
        {
            if (chars[i] == ' ')
            {
                countSpace++;
                if (countSpace == 4)
                {
                    lastSpaceIndex = string.length();
                } else if (countSpace == 5)
                {
                    lastSpaceIndex = i;
                    break;
                }
            }
        }
        if (countSpace < 4)
        {
            throw new TooShortStringException();
        }
        return string.substring(firstSpaceIndex, lastSpaceIndex);

/*
        if (string == null || string.isEmpty())
            throw new TooShortStringException();
        int offset = string.indexOf(" ") + 1;
        int pos = offset;
        String result = "";
        for (int i = 0; i < 4; i++) {
            pos = string.indexOf(" ", pos);
            if (pos == -1)
                throw new TooShortStringException();
        }
        result = string.substring(offset, pos);
        if (!Character.isLetter(result.charAt(result.length() - 1)))
            throw new TooShortStringException();

        return result;
*/
    }

    public static class TooShortStringException extends Exception {
    }
}
