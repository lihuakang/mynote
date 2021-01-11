package com.dl.java8;

import com.dl.ll.A;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
                .collect(toList());
        System.out.println(collect.toString());
    }

    @Test
    public void test3(){
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);//打印每个元素的长度
    }
    @Test
    public void test4(){
        List<String> words = Arrays.asList("Hello", "World");
        List<String> wordArr = words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(wordArr);//打印每个元素的长度
    }
    @Test
    public void test5(){
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .findAny()
                .ifPresent(s-> System.out.println(s));//如果存在就执行下面代码
    }

    @Test
    public void test6(){
        //找到平方能被3整除的第一个数
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        words.stream()
                .map(x->x*x)
                .filter(x->x%3==0)
                .findFirst()
                .ifPresent(s-> System.out.println(s));//9
    }
    @Test
    public void test7(){
        //元素求和
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        Integer reduce = words.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
    }
    @Test
    public void test8(){
        //元素求和
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        words.stream()
                .reduce( (a, b) -> a + b).ifPresent(x-> System.out.println(x));
    }
    @Test
    public void test9(){
        //元素最大值
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        Optional<Integer> reduce = words.stream()
                .reduce(Integer::max);
        reduce.ifPresent(x-> System.out.println(x));
    }
    @Test
    public void test10(){
        //元素最大值
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        //返回值时Option
        OptionalDouble average = words.stream()
                .mapToInt(Integer::intValue)
                .average();
        average.ifPresent(a-> System.out.println(a));
//        System.out.println(sum);
        int reduce = words.stream()
                .reduce(0, Integer::sum);

    }
    @Test
    public void test11(){
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        IntStream intStream = words.stream().mapToInt(Integer::intValue);
        Stream<Integer> boxed = intStream.boxed();
    }
    @Test
    public void test12(){
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        IntStream intStream = words.stream().mapToInt(Integer::intValue);
        IntStream intStream1 = IntStream.range(1, 100);
        int sum = intStream1.sum();
        System.out.println(sum);
    }
    @Test
    //无限流
    public void test13(){
        Stream.iterate(0,n->n+2)
                .limit(10)
                .forEach(System.out::println);
    }
    @Test
    //无限流
    public void test14(){
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }
    @Test
    public void test15(){
        List<Integer> words=Arrays.asList(1,2,3,4,5,6,7,8);
        Long collect = words.stream().collect(Collectors.counting());
        long count = words.stream().count();
        Optional<Integer> collect1 = words.stream().collect(maxBy((a, b) -> a.compareTo(b)));
        collect1.ifPresent(x-> System.out.println(x));
        System.out.println(collect);

        Double collect2 = words.stream().collect(averagingInt(Integer::intValue));
        System.out.println(collect2);

        String collect3 = words.stream().map(a -> a.toString()).collect(joining(", "));
        System.out.println(collect3);//1, 2, 3, 4, 5, 6, 7, 8

    }

    @Test
    public void test16(){
        //分组
        List<Apple> list=new ArrayList();
        Apple apple1=new Apple("red",11);
        Apple apple2=new Apple("red",2);
        Apple apple3=new Apple("green",13);
        Apple apple4=new Apple("green",14);
        Apple apple5=new Apple("black",22);
        list.add(apple1);
        list.add(apple2);
        list.add(apple3);
        list.add(apple4);
        list.add(apple5);
        Map<String, List<Apple>> collect = list.stream().collect(groupingBy(Apple::getColor));
        System.out.println(collect);
        Map<String, List<Apple>> collect1 = list.stream().collect(groupingBy((a) -> {
            if (a.getWeight() > 20) {
                return "a";
            } else {
                return "b";
            }
        }));
        System.out.println(collect1);

        Map<String, Map<String, List<Apple>>> collect2 = list.stream().collect(groupingBy(Apple::getColor, groupingBy((a) -> {
            if (a.getWeight() > 20) {
                return "a";
            } else {
                return "b";
            }
        })));
        System.out.println(collect2);

        Map<String, Long> collect3 = list.stream().collect(groupingBy(Apple::getColor, counting()));
        System.out.println(collect3);

        Map<String, Apple> collect4 = list.stream().collect(groupingBy(Apple::getColor, collectingAndThen(maxBy(Comparator.comparingInt(Apple::getWeight)), Optional::get)));
        System.out.println(collect4);
    }
    @Test
    public void test17(){
        //分区
        List<Apple> list=new ArrayList();
        Apple apple1=new Apple("red",11);
        Apple apple2=new Apple("red",2);
        Apple apple3=new Apple("green",13);
        Apple apple4=new Apple("green",14);
        Apple apple5=new Apple("black",22);
        list.add(apple1);
        list.add(apple2);
        list.add(apple3);
        list.add(apple4);
        list.add(apple5);
        Map<Boolean, List<Apple>> collect = list.stream().collect(partitioningBy(x -> x.getWeight() > 10));
        System.out.println(collect);
        List<Apple> list1 = collect.get(true);
        System.out.println(list1);
        Supplier<List> supplier=()->new ArrayList<>();
        supplier.get().add("aa");
        Object collect1 = supplier.get().stream().collect(toList());
        System.out.println(collect1);
    }
    @Test
    public void test18(){
        Long reduce = Stream.iterate(0L, i -> i + 1)
                .limit(100)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(reduce);
    }
    @Test
    public void test19(){
        //声明一个空的Optional
        Optional<Apple> optional=Optional.empty();
        //依据一个非空值创建Optional
        Apple apple=new Apple();
        Optional<Apple> optApple=Optional.of(apple);
        //可接受null的Optional
        Optional<Apple> apple1 = Optional.ofNullable(apple);
        Optional<String> s = apple1.map(Apple::getColor);
    }
}
