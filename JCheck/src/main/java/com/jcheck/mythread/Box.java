package com.jcheck.mythread;

/**
 * 奶箱类
 */
public class Box {
    private int i;
    private boolean state=false;

    public synchronized void put(int i){
        if(state){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.i=i;
        System.out.println("送奶工将第"+this.i+"瓶奶放入奶箱");
        this.state=true;
        //唤醒等待的线程
        notifyAll();
    }

    public synchronized void get(){
        if(!state){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("用户拿到第"+this.i+"瓶奶");
        this.state=false;
        //唤醒等待的线程
        notifyAll();
    }

}
