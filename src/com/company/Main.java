package com.company;

public class Main {

    public static void main(String[] args) {
        Logs statistic1 = new Logs();
        Logs statistic2 = new Logs();
        Logs statistic3 = new Logs();
        Model model1 = new Model(statistic1);
        Model model2 = new Model(statistic2);
        Model model3 = new Model(statistic3);
        model1.start();
        model2.start();
        model3.start();
        try {
            model1.join();
            statistic1.getFinalStatistic();
            model2.join();
            statistic2.getFinalStatistic();
            model3.join();
        } catch (Exception e) {}
    }
}