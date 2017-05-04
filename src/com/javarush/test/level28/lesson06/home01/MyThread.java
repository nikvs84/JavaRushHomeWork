package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 22.10.2016.
 */
public class MyThread extends Thread {
    private static AtomicInteger threadPriority = new AtomicInteger(Thread.MIN_PRIORITY - 1);
    public MyThread() {
        super();
        this.setPriority(getNextPriority());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(getNextPriority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(getNextPriority());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(getNextPriority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(getNextPriority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(getNextPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(getNextPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(getNextPriority());
    }

    private int getNextPriority() {
        threadPriority.incrementAndGet();
        threadPriority.compareAndSet(11, 1);

        int newPriority = threadPriority.get();
        if (getThreadGroup() != null) {
            if (newPriority > getThreadGroup().getMaxPriority()) {
                newPriority = getThreadGroup().getMaxPriority();
            }
        }
        return newPriority;
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        for (int i = 0; i < 25; i++) {
            System.out.print(thread.getNextPriority() + " ");
        }
    }

}
