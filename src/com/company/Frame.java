package com.company;

public class Frame extends Thread{
    private Boolean flag = true;
    private Logs logs;

    public Frame(Logs logs) {
        this.logs = logs;
    }

    public void changeFlag() {
        this.flag = !flag;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(3000);
                logs.getIntermediateStatistics();
            } catch (Exception e) {}
        }
    }

}
