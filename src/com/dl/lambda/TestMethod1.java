package com.dl.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;

public class TestMethod1 {
    @Test
    public void test1(){
        PrintStream out = System.out;
        Consumer consumer=(x)->out.println(x);
        PrintStream out1 = System.out;
        Consumer runnable = out1::println;
    }
    @Test
    public void test2(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
//        Comparator<Integer> compare = Integer::compare;
//        int compare1 = compare.compare(1, 2);
        int compare1 = com.compare(2, 3);
        System.out.println(compare1);
    }
    public void test3(){

    }
}
