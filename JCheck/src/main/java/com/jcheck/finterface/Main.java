package com.jcheck.finterface;

public class Main {
    public static void main(String[] args) {
        MyInterface m =()->{
            System.out.println("函数式接口");
        };

        m.show();
    }
}
