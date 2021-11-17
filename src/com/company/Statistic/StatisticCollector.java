package com.company.Statistic;

import com.company.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class StatisticCollector {
    private int refusals;
    private List<Future<Result>> results;

    public StatisticCollector() {
        results = new ArrayList<>();
    }

    public void increaseRefusals() {
        refusals++;
    }

    public void addFuture(Future<Result> result) {
        results.add(result);
    }

    public Statistic collect() throws Exception{
        Statistic statistic = new Statistic();
        statistic.setRefusals(refusals);

        int size = 0;

        double sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).isDone()) {
                size++;
                sum2 += results.get(i).get().getQueueLength();
                sum1 += results.get(i).get().getTime();
            }
        }

        statistic.setRefusalsPercentage((double) (refusals * 100) / (refusals + size));
        statistic.setClients(size);
        statistic.setAverageTimePerClient(sum1 / size);
        statistic.setAverageQueueLength(sum2 / size);

        return statistic;
    }
}
