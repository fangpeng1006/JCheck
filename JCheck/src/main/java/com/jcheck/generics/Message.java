package com.jcheck.generics;

public class Message<T> {
    private T msg;
    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

}
