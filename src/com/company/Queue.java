package com.company;

public class Queue {
    final int maxLength;
    private int length;
    private int busy;
    private Pair head;
    private Pair tail;

    public Queue (int maxLength) {
        this.maxLength = maxLength;
    }

    public synchronized int getLength() {
        return this.length;
    }

    public synchronized Client take() throws InterruptedException{
        while (length == 0 || busy == this.maxLength) {
            wait();
        }
        Client client = head.getHead();
        head = head.getTail();
        this.busy++;
        this.length--;
        return client;
    }

    public synchronized void put(Client client) {
        if (head == null) {
            head = new Pair(client);
            tail = head;
        } else {
            tail.setTail(new Pair(client));
            tail = tail.getTail();
        }
        this.length++;
        notifyAll();
    }

    public synchronized void free() {
        busy--;
        notifyAll();
    }
}
