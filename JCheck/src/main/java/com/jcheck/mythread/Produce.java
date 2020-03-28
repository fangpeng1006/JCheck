package com.jcheck.mythread;

/**
 * 生产者
 */
public class Produce implements Runnable {
    private Box b;
    public Produce(Box b) {
        this.b=b;
    }

    public void run() {
        for(int i=1;i<=500;i++){
            b.put(i);
        }
    }
}
