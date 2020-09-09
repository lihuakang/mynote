package com.dl.lambda;

import javafx.beans.binding.IntegerExpression;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class TestLambda1 {
    @Test
    //无参数无返回值
    public void test1() {
        //jdk 1.8 final可以省略
        final int num=1;
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello"+num);
            }
        };
        runnable.run();
        System.out.println("-----------------------------");
        //无参数无返回值的lambda
        Runnable runnable1=()-> System.out.println("Hellow");
        runnable1.run();
    }
    @Test
    //有一个参数无返回值
    public void test2(){
        //一个参数可以省略小括号不写
        Consumer<String> consumer=(x)-> System.out.println(x);
        consumer.accept("武汉加油");
    }
    @Test
    public void test3(){

        Comparator<Integer> comparable=(x, y)->{
            System.out.println("比较");
            return Integer.compare(x,y);
        };
        int compare = comparable.compare(3, 5);
        System.out.println(compare);
    }
    @Test
    public void test4(){
        //只有一条语句，return和大括号可以省略
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);
        int compare = comparator.compare(7, 5);
        System.out.println(compare);
    }
}
