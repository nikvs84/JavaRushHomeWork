package com.javarush.test.level17.lesson10.bonus02;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public volatile static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            String command = args[0];
            switch (command) {
                case "-c": {
                    for (int i = 3; i < args.length; i += 3) {
                        createRecord(args[i - 2], args[i - 1], dateFormat.parse(args[i]));
                    }
                } break;
                case "-u": {
                    for (int i = 4; i < args.length; i += 4) {
                        updateRecord(Integer.parseInt(args[i-3]), args[i - 2], args[i - 1], dateFormat.parse(args[i]));
                    }
                } break;
                case "-d": {
                    for (int i = 1; i < args.length; i++) {
                        deleteRecord(Integer.parseInt(args[i]));
                    }
                } break;
                case "-i": {
                    for (int i = 1; i < args.length; i++) {
                        infoRecord(Integer.parseInt(args[i]));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public static synchronized void createRecord(String name, String sex, Date bd) {
        if ("м".equals(sex))
            allPeople.add(Person.createMale(name, bd));
        else
        if ("ж".equals(sex))
            allPeople.add(Person.createFemale(name, bd));
        System.out.println(allPeople.size() - 1);
    }

    public static synchronized void updateRecord(int id, String name, String sex, Date bd) {
        allPeople.get(id).setName(name);
        allPeople.get(id).setBirthDay(bd);
        if ("м".equals(sex))
            allPeople.get(id).setSex(Sex.MALE);
        else
        if ("ж".equals(sex))
            allPeople.get(id).setSex(Sex.FEMALE);
    }

    public static synchronized void deleteRecord(int id) {
        allPeople.get(id).setBirthDay(null);
    }

    public static void infoRecord(int id) {
        Person person = allPeople.get(id);
        if (person.getName() == null) {
            System.out.println("null");
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String result = person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж") + " " + dateFormat.format(person.getBirthDay());
            System.out.println(result);
        }
    }

}
