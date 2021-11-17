package com.company;

import java.util.concurrent.ExecutorService;

public class ClientProducer extends Thread {
    final int maxLength = 18;
    final int minTime = 2000;
    final int maxTime = 3000;
    private Queue queue;
    private  ExecutorService executor;
    private Logs logs;

    public ClientProducer(Queue queue, ExecutorService executor, Logs logs) {
        this.queue = queue;
        this.executor = executor;
        this.logs = logs;
    }

    @Override
    public synchronized void run() {
        while (!executor.isShutdown()) {
            try {
                Thread.sleep((int) (this.minTime +
                        Math.random() * (this.maxTime - this.minTime)));
                if (queue.getLength() < this.maxLength) {
                    queue.put(new Client(queue));
                } else {
                    logs.incrementRefusals();
                }
            } catch (InterruptedException e) {}
        }
    }
}
