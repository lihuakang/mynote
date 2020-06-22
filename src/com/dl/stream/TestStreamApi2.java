package com.dl.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作   筛选和切片
 * filter--接受lambda，从流中排除某些元素
 * limit--截断流，使元素不超过给定数量
 * skip(n)--跳过元素，返回一个扔掉了前n个元素得流，若流中元素不足n个，则返回一个空流。与limit(n)互补
 * distinct--筛选，通过流所生成得元素得hashCode（）和equals（）去掉重复得元素
 */
public class TestStreamApi2 {

    List<Employee> employees= Arrays.asList(
            new Employee("张三",23),
            new Employee("李四",12),
            new Employee("李四",12),
            new Employee("李四",12),
            new Employee("王五",2)
    );
    @Test
    public void test1(){
        Stream<Employee> stream = employees.stream()
                .filter((e) -> e.getAge() > 15);
        stream.forEach(System.out::println);
    }
    @Test
    public void test2(){
        employees.stream()
                .filter((e)->e.getAge()>1)
                .skip(1)
                .forEach(System.out::println);
    }

    @Test
    public void test3(){
        employees.stream()
                .filter((e)->e.getAge()>11)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 中间操作
     * 映射
     * map--接口Lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，该函数会被应用到每个元素傻瓜，并/映射成一个新的元素
     * flatMap--接受一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流连城一个流
     */
    @Test
    public void test4(){
        //把每个参数都映射
        List<String> list=Arrays.asList("aaa","xx","ssss","cccc");
        list.stream()
                .map((str)->str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("-------------------------");
        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamApi2::filterCharacter);
        streamStream.forEach((sm)->{
            sm.forEach(System.out::println);
        });

        System.out.println("======================");
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamApi2::filterCharacter);
        characterStream.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list=new ArrayList<>();
        for(Character ch:str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }


}
