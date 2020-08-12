package com.jcheck.generics;

import java.util.ArrayList;

public class JGenerics {
    public static void main(String[] args) {
        Message<ArrayList> m = new Message<ArrayList>();
        m.setMsg(new ArrayList());
        func(m);
    }
    public static void func(Message<?> m){
        System.out.println(m.getMsg());
    }
}
