package com.company;

public class Pair {
    private Client head;
    private Pair tail;
    public Pair(Client head) {
        this.head = head;
    }
    public Client getHead() {
        return head;
    }
    public void setTail(Pair tail) { this.tail = tail; }
    public Pair getTail() {
        return tail;
    }
}
