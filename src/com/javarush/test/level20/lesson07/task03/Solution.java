package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("firstName", "lastName", 27);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\a03.txt"));
        person.writeExternal(objectOutputStream);
        objectOutputStream.close();
        Person person1 = new Person();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\a03.txt"));
        person1.readExternal(objectInputStream);
        objectInputStream.close();
        System.out.println(person1.firstName);
        System.out.println(person1.lastName);
        System.out.println(person1.age);
        System.out.println(person1.father);
        System.out.println(person1.mother);
        System.out.println(person1.children);
    }

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {}

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(father);
            out.writeObject(mother);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String) in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            father = (Person)in.readObject();
            mother = (Person)in.readObject();
            children = (List<Person>)in.readObject();
        }
    }
}
