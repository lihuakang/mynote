package com.dl.shejimoshi.strategy;

/**
 * 这是赵云
 */
public class Zhaoyun {
    public static void main(String[] args) {
        Context context;

        System.out.println("刚到吴国拆开第一个");
        context=new Context(new BackDoor());
        context.operate();
        System.out.println();


        System.out.println("拆开第二个妙计");
        context=new Context(new GivenGreenLight());
        context.operate();


        System.out.println("拆开第三个");
        context=new Context(new BlockEnemy());
        context.operate();
    }
}
