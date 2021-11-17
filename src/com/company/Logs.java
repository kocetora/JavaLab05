package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Logs {
    private int averageQueueLength;
    private int refusals;
    private double refusalsRate;
    private double averageClientTime;
    private int clients;
    private List<Future<Result>> results;

    public Logs() {
        this.results = new ArrayList<>();
    }

    public void incrementRefusals() {
        this.refusals++;
    }

    public void addFuture(Future<Result> result) {
        this.results.add(result);
    }

    public void print(){
        System.out.println("Average Queue Length: " + this.averageQueueLength + "\n" +
                           "Clients: " + this.clients + "\n" +
                           "Average Client Time: " + this.averageClientTime + "\n" +
                           "Refusals: " + this.refusals + "\n" +
                           "Refusals rate: " + this.refusalsRate + "\n");
    }

    public void getFinalStatistic() {
        this.averageQueueLength = this.results.stream().mapToInt(el -> {
            try {
                return el.get().getQueueLength();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum() / this.results.size();
        this.clients = this.results.size();
        this.averageClientTime = this.results.stream().mapToDouble(el -> {
            try {
                return el.get().getTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum() / results.size();
        this.refusalsRate = (double) (this.refusals * 100) / (this.refusals + results.size());
        System.out.println("FINAL");
        this.print();
    }

    public void getIntermediateStatistics() throws Exception{
        int size = 0;
        double totalTime = 0;
        int totalLength = 0;
        for (int i = 0; i < this.results.size(); i++) {
            if (this.results.get(i).isDone()) {
                size++;
                totalTime += this.results.get(i).get().getTime();
                totalLength += this.results.get(i).get().getQueueLength();
            }
        }
        this.averageQueueLength = totalLength / size;
        this.clients = size;
        this.averageClientTime = totalTime / size;
        this.refusalsRate = (double) (this.refusals * 100) / (this.refusals + size);
        this.print();
    }
}
