package com.company;

import com.company.Statistic.LoggerThread;
import com.company.Statistic.StatisticCollector;

import java.util.concurrent.*;

public class Model extends Thread {
    final int threads = 5;
    private int id;
    private StatisticCollector statistic;

    public Model(int id, StatisticCollector statistic) {
        this.id = id;
        this.statistic = statistic;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        Queue queue = new Queue(threads);

        ClientProducer clientProducer = new ClientProducer(queue, executor, statistic, id);
        clientProducer.start();

        Shop shop = new Shop(executor, queue, statistic);
        shop.start();

        LoggerThread loggerThread = new LoggerThread(statistic, executor, id);
        loggerThread.start();

        try {
            clientProducer.join();
            shop.join();
            loggerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
