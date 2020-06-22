package com.dl.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * java8内置了4大核心函数式接口
 * Consumer<T>  消费型接口
 *  void accept(T t);
 *
 *  Supplier<T>  供给型接口
 *  T get();
 *
 *  Function<T,R> 函数型接口
 *  R  apply(T t);
 *
 *  Predicate<T> 断言型接口
 *   boolean   test(T,t);
 *
 */
public class TestLambda2 {
    @Test
    public void test1(){
        happy(10000.00,(x)-> System.out.println("消费了"+x+"元"));
    }
    public void happy(double money,Consumer<Double> con){
        con.accept(money);
    }
}
