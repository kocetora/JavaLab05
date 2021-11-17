package com.company;

import java.util.concurrent.ExecutorService;

public class Accountant extends Thread {
    final int maxClients = 12;
    private ExecutorService executor;
    private Queue queue;
    private int clients;
    private Logs logs;

    public Accountant(ExecutorService executor, Queue queue, Logs logs) {
        this.executor = executor;
        this.queue = queue;
        this.logs = logs;
    }


    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                logs.addFuture(executor.submit(queue.take()));
                if (++clients >= this.maxClients) {
                    executor.shutdown();
                    break;
                }
            } catch (InterruptedException e) {}
        }
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(7000);
            } catch (Exception e) {}
        }
    }
}