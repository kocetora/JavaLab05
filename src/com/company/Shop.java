package com.company;

import com.company.Statistic.StatisticCollector;

import java.util.concurrent.ExecutorService;

public class Shop extends Thread {
    final int clients = 1000;
    final int sleep = 1000;
    private ExecutorService executor;
    private Queue queue;
    private int client;
    private StatisticCollector statistic;

    public Shop(
            ExecutorService executor,
            Queue queue,
            StatisticCollector statistic
    ) {
        this.executor = executor;
        this.queue = queue;
        this.statistic = statistic;
    }

    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                statistic.addFuture(executor.submit(queue.take()));
                if (++client >= this.clients) {
                    executor.shutdown();
                    break;
                }
            } catch (InterruptedException e) {}
        }
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(sleep);
            } catch (Exception e) {}
        }
    }
}