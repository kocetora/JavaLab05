package com.company;

import com.company.Statistic.StatisticCollector;

public class Main {

    public static void main(String[] args) throws Exception {
        final int modelQuantity = 4;
        StatisticCollector[] statistics = new StatisticCollector[modelQuantity];
        Model[] models = new Model[modelQuantity];
        for (int i = 0; i < modelQuantity; i++) {
            statistics[i] = new StatisticCollector();
            models[i] = new Model(i + 1, statistics[i]);
            models[i].start();
        }
        for (int i = 0; i < modelQuantity; i++) {
            models[i].join();
        }
        for (int i = 0; i < modelQuantity; i++) {
            System.out.println("\nFinal statistic for model number " + (i + 1) + ":" + statistics[i].collect());
        }
    }
}