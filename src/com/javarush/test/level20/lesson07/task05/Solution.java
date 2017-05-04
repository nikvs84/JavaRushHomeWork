package com.javarush.test.level20.lesson07.task05;

import java.io.*;

/* Переопределение сериализации
Сделайте так, чтобы после десериализации нить runner продолжила работать.
Ключевые слова объекта runner менять нельзя.
Hint/Подсказка:
Конструктор не вызывается при сериализации, только инициализируются все поля.
*/
public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public static void main(String[] args) throws InterruptedException, IOException {
/*
        Solution solution = new Solution(1);
        Thread.sleep(5000);
        FileOutputStream outputStream = new FileOutputStream("d:\\a05.txt");
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        solution.writeObject(oos);
        oos.close();
        outputStream.close();
*/

    }

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        /*System.out.println();
        while(!Thread.currentThread().isInterrupted() && speed < 50) {
            System.out.print(speed++ + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                break;
            }
        }
*/
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
/*
        this.runner.interrupt();
        System.out.println("Interrupt");
        out.writeObject(this);
*/
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread();
        runner.start();
/*
        this.runner = ((Solution) in.readObject()).runner;
        this.runner.start();
*/
    }
}
