package com.company;

import java.util.concurrent.Callable;

public class Client implements Callable<Result> {
    final int minTime = 60;
    final int maxTime = 120;
    private Queue queue;
    private int id;
    private int modelId;

    public Client(Queue queue, int id, int modelId) {
        this.id = id;
        this.queue = queue;
        this.modelId = modelId;
    }

    @Override
    public Result call() {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep((int) (minTime +
                    Math.random() * (this.maxTime - this.minTime)));
        } catch (InterruptedException e) {}
        queue.free();
        return new Result((System.currentTimeMillis() - startTime) / 1000.0, queue.getLength());
    }
}
