package com.company.Statistic;

import java.util.concurrent.ExecutorService;

public class LoggerThread extends Thread{
    int sleep = 1000;
    private StatisticCollector statisticCollector;
    private ExecutorService executor;
    private int modelId;

    public LoggerThread(StatisticCollector statisticCollector, ExecutorService executor, int modelId) {
        this.statisticCollector = statisticCollector;
        this.executor = executor;
        this.modelId = modelId;
    }

    @Override
    public void run() {
        while (!executor.isShutdown()) {
            try {
                Thread.sleep(sleep);
                System.out.println("\nLogging statistic for the model " + modelId +  statisticCollector.collect());
            } catch (Exception e) {}
        }
    }
}
