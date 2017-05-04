package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Admin on 30.10.2016.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {

    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask binRepr = new BinaryRepresentationTask(b);
            binRepr.fork();
            result = binRepr.join() + result;
        }
        return result;
    }
}
