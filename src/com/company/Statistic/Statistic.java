package com.company.Statistic;

public class Statistic {
    private int refusals;
    private double refusalsPercentage;
    private double averageTimePerClient;
    private int averageQueueLength;
    private int clients;

    public void setRefusals(int refusals) {
        this.refusals = refusals;
    }
    public void setRefusalsPercentage(double refusalsPercentage) {
        this.refusalsPercentage = refusalsPercentage;
    }
    public void setAverageTimePerClient(double averageTimePerClient) {
        this.averageTimePerClient = averageTimePerClient;
    }
    public void setAverageQueueLength(int averageQueueLength) {
        this.averageQueueLength = averageQueueLength;
    }
    public void setClients(int clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "\nClients: " + clients
                + "\nRefusals: " + refusals
                + "\nRefusals percentage: " + refusalsPercentage
                + "\nAverage time per client: " + averageTimePerClient
                + "\nAverage queue length: " + averageQueueLength
                + "\n";
    }
}
