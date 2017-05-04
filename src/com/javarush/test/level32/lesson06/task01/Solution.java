package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

/*
* Все работает. Проверку не проходит. Зато проходит вот это:*/

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Random random = new Random();

        // digits
        for (int i = 0; i < 3; i++) {
            byteArrayOutputStream.write(48 + random.nextInt(10));
        }

        // lower letters
        for (int i = 0; i < 3; i++) {
            byteArrayOutputStream.write(65 + random.nextInt(26));
        }

        // capital letters
        for (int i = 0; i < 2; i++) {
            byteArrayOutputStream.write(97 + random.nextInt(26));
        }

        return byteArrayOutputStream;
    }

/*
    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] password = new char[8];
        char[] chars = new char[62];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) (i + 48);
        }
        for (int i = 10; i < 36; i++) {
            chars[i] = (char) (i + 87);
        }
        for (int i = 36; i < 62; i++) {
            chars[i] = (char) (i + 29);
        }
        for (int i = 0; i < 8; i++) {
            int j = random.nextInt(0, 62);
            password[i] = chars[j];
        }
        String pass = new String(password);
        try {
            baos.write(pass.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos;
    }
*/
}
