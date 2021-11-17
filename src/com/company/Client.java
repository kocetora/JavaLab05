package com.company;

import java.util.concurrent.Callable;

public class Client implements Callable<Result> {
    final int minTime = 10000;
    final int maxTime = 15000;
    private Queue queue;

    public Client(Queue queue) {
        this.queue = queue;
    }

    @Override
    public Result call() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep((int) (minTime +
                    Math.random() * (this.maxTime - this.minTime)));
        } catch (InterruptedException e) {}
        queue.free();
        return new Result((System.currentTimeMillis() - start) / 1000.0, queue.getLength());
    }
}
