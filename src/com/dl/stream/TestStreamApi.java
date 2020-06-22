package com.dl.stream;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * StreamAPI流 三个步骤
 *  1.创建Stream
 *  2.中间操作
 *  3终止操作
 */
public class TestStreamApi {
    @Test
    public void test1(){
    //创建Stream   四种创建方式
        //1。可以通过Collection系列集合提供的stream()方法parallelStream
        List<String> list=new ArrayList<String>();
        Stream<String> stream = list.stream();

        //2通过Arrays中得静态方法stream()获取数组流
        Employee[] employees=new Employee[10];
        Stream<Employee> stream1= Arrays.stream(employees);

        //3.通过Stream类中得静态方法of（）
        Stream<String> stream2=Stream.of("aa","bb","cc");
        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);
        //生成
        Stream.generate(()->Math.random())
                .limit(5)
                .forEach(System.out::println);
    }
}
