package com.dl.datastructure;

public class Test {
    public static void main(String[] args) {
        Linked linked=new DoubleLinked();
        linked.add(3);
        linked.add(7);
        linked.add(6);
        Object o = linked.get(3);
        System.out.println(o);
        System.out.println(linked.size());
    }
}
