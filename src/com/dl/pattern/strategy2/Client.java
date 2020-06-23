package com.dl.pattern.strategy2;

public class Client {
    public static void main(String[] args) {
        System.out.println("未使用模式");
        NoStrategy noStrategy=new NoStrategy();
        double price=noStrategy.getPrice("普通客户大批量",1000);
        System.out.println(price);


        System.out.println("=========使用策略===========");
        Context context=new Context(new Vip1Strategy());
        double b = context.getReultPrice(1000);
        System.out.println(b);
    }
}
