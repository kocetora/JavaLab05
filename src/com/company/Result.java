package com.company;

public class Result {
    private double timePerClient;
    private int queueLength;

    public Result(double timePerClient, int queueLength) {
        this.timePerClient = timePerClient;
        this.queueLength = queueLength;
    }

    public double getTime() {
        return timePerClient;
    }

    public int getQueueLength() {
        return queueLength;
    }
}
