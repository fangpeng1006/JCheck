package com.jcheck.lambda.simple;

public class UseEat {
    public static void main(String[] args) {
        //方式1：通过接口的实现类
        EatableImpl e = new EatableImpl();
        useEat(e);
        System.out.println("-------------------------");

        //方式2：匿名内部类
        useEat(new Eatable() {
            public void eat() {
                System.out.println("匿名内部类");
            }
        });
        System.out.println("-------------------------");

        //方式3：lambda表达式
        useEat(()->{
            System.out.println("lambda");
        });
    }

    public static void useEat(Eatable e){
        e.eat();
    }
}
