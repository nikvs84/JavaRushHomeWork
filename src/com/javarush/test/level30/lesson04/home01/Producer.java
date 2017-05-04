package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Admin on 28.10.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 1;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (i < 10) {
                    System.out.format("Элемент 'ShareItem-%d' добавлен", i);
                    System.out.println();
                    queue.offer(new ShareItem("ShareItem-" + i, i++));
                    Thread.sleep(100);
                    if (queue.hasWaitingConsumer()) {
                        System.out.println("Consumer в ожидании!");
                    }
                }
            }
        } catch (InterruptedException ignore) {

        }
    }
}
