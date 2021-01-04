package com.dl.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DemoLamda01 {

    @Test
    public void test1(){
//        (Apple a)->a.getWeight()
//        Function<Apple, Integer> getWeight = Apple::getWeight;
//        System.out.println(getWeight);
//        String str="123";
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort(String::compareTo);
//        str.sort((s1,s2)->s1.compareTo(s2));
        System.out.println(str);
    }


    @Test
    public void test2(){
        List<Integer> integers = Arrays.asList(1, 1, 2, 4, 2, 4, 5);
        List<Integer> collect = integers.stream()
                .skip(3)  //扔掉前面三个元素
                .collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
