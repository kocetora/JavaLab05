package com.company;

import java.util.concurrent.*;

public class Model extends Thread {
    final int maxLength = 5;
    private Logs logs;

    public Model(Logs logs) {
        this.logs = logs;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(this.maxLength);
        Queue queue = new Queue(this.maxLength);

        ClientProducer clientProducer = new ClientProducer(queue, executor, logs);
        clientProducer.start();

        Accountant accountant = new Accountant(executor, queue, logs);
        accountant.start();

        Frame thread = new Frame(logs);
        thread.start();

        try {
            clientProducer.join();
            accountant.join();
            thread.join();
            thread.changeFlag();
        } catch (Exception e){ }
    }
}
