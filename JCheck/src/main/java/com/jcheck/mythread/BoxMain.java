package com.jcheck.mythread;

public class BoxMain {
    public static void main(String[] args) {
        Box b=new Box();
        Produce p=new Produce(b);
        Customer c=new Customer(b);

        Thread t1=new Thread(p);
        Thread t2=new Thread(c);

        t1.start();
        t2.start();
    }
}
