package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        Scanner scanner;
        PersonScannerAdapter(Scanner scanner) {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String[] sp = scanner.nextLine().split(" ");
            Date bd = new GregorianCalendar(Integer.parseInt(sp[5]), Integer.parseInt(sp[4]) - 1, Integer.parseInt(sp[3]), 0, 0, 0).getTime();
            return new Person(sp[1], sp[2], sp[0], bd);
        }

        @Override
        public void close() throws IOException {
            this.scanner.close();
        }
    }
}
