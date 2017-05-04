package com.javarush.test.level40.lesson10.task01;

/* Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код

//        String shortDate = "'День:' d\n" +
//                "'День недели:' F\n" +
//                "'День месяца:' d\n" +
//                "'День года:' D\n" +
//                "'Неделя месяца:' W\n" +
//                "'Неделя года:' w\n" +
//                "'Месяц:' M\n" +
//                "'Год:' yyyy\n" +
//                "'Эра:' G";
//        String time = "'AM или PM:' a\n" +
//                "'Часы:' h\n" +
//                "'Часы дня:' H\n" +
//                "'Минуты:' m\n" +
//                "'Секунды:' s";

        String shortDate = "День: %d\n" +
                "День недели: %d\n" +
                "День месяца: %d\n" +
                "День года: %d\n" +
                "Неделя месяца: %d\n" +
                "Неделя года: %d\n" +
                "Месяц: %d\n" +
                "Год: %d\n" +
                "Эра: %d";
        String time = "AM или PM: %d\n" +
                "Часы: %d\n" +
                "Часы дня: %d\n" +
                "Минуты: %d\n" +
                "Секунды: %d";


        String pattern = "dd.M.yyyy";
        String printPattern;
//        printPattern = shortDate;
        int selector = 1;
        if (date.contains(" ")) {
            selector = 2;
            pattern += " HH:mm:ss";
//            printPattern = shortDate + "\n" + time;
        } else {
            if (date.contains(":")) {
                selector = 3;
                pattern = "HH:mm:ss";
//                printPattern = time;
            }
        }

        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        dateFormat = new SimpleDateFormat(printPattern);
//        String answer = dateFormat.format(calendar.getTime());
        switch (selector) {
            case 1:
                showDateInfo(calendar);
                break;
            case 2:
                showDateInfo(calendar);
                showTimeInfo(calendar);
                break;
            case 3:
                showTimeInfo(calendar);
                break;
        }
    }

    private static void showTimeInfo(Calendar cal)
    {
        System.out.println("AM или PM: " + cal.get(Calendar.AM_PM));
        System.out.println("Часы: " + cal.get(Calendar.HOUR));
        System.out.println("Часы дня: " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + cal.get(Calendar.MINUTE));
        System.out.println("Секунды: " + cal.get(Calendar.SECOND));
    }

    private static void showDateInfo(Calendar cal)
    {
        System.out.println("День: " + cal.get(Calendar.DATE));
        System.out.println("День недели: " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("День месяца: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + cal.get(Calendar.MONTH));
        System.out.println("Год: " + cal.get(Calendar.YEAR));
        System.out.println("Эра: " + cal.get(Calendar.ERA));
    }
}
