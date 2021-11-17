package com.company;

import com.company.Statistic.StatisticCollector;

import java.util.concurrent.ExecutorService;

public class ClientProducer extends Thread {
    final int maxLength = 3;
    final int minTime = 7;
    final int maxTime = 14;
    private Queue queue;
    private ExecutorService executor;
    private StatisticCollector statistic;
    private int modelId;
    private int count;

    public ClientProducer(Queue queue, ExecutorService executor, StatisticCollector statistic, int modelId) {
        this.queue = queue;
        this.executor = executor;
        this.statistic = statistic;
        this.modelId = modelId;
    }

    @Override
    public synchronized void run() {
        while (!executor.isShutdown()) {
            try {
                Thread.sleep((int) (this.minTime +
                        Math.random() * (this.maxTime - this.minTime)));
                if (queue.getLength() < this.maxLength) {
                    queue.put(new Client(queue, ++count, modelId));
                } else {
                    statistic.increaseRefusals();
                }
            } catch (InterruptedException e) {}
        }
    }
}
