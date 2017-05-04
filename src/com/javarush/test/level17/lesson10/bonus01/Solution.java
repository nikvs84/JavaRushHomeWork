package com.javarush.test.level17.lesson10.bonus01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            if ("-c".equals(args[0]))
                createRecord(args[1], args[2], dateFormat.parse(args[3]));
            else if ("-u".equals(args[0]))
                updateRecord(Integer.parseInt(args[1]), args[2], args[3], dateFormat.parse(args[4]));
            else if ("-d".equals(args[0]))
                deleteRecord(Integer.parseInt(args[1]));
            else if ("-i".equals(args[0]))
                infoRecord(Integer.parseInt(args[1]));
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public static void createRecord(String name, String sex, Date bd) {
        if ("м".equals(sex))
        allPeople.add(Person.createMale(name, bd));
        else
            if ("ж".equals(sex))
                allPeople.add(Person.createFemale(name, bd));
        System.out.println(allPeople.size() - 1);
    }

    public static void updateRecord(int id, String name, String sex, Date bd) {
        allPeople.get(id).setName(name);
        allPeople.get(id).setBirthDay(bd);
        if ("м".equals(sex))
            allPeople.get(id).setSex(Sex.MALE);
        else
            if ("ж".equals(sex))
                allPeople.get(id).setSex(Sex.FEMALE);
    }

    public static void deleteRecord(int id) {
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
