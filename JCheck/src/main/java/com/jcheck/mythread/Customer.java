package com.jcheck.mythread;

public class Customer implements Runnable {
    private Box b;

    public Customer(Box b) {
        this.b = b;
    }

    public void run() {
        while (true) {
            this.b.get();
        }

    }
}
