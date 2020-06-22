package com.dl.stream;

import org.junit.Test;

import javax.jnlp.ClipboardService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamApi3 {
    List<Employee> employees= Arrays.asList(
            new Employee("张三",23),
            new Employee("李四",12),
            new Employee("李四",12),
            new Employee("李四",12),
            new Employee("王五",2)
    );
    @Test
    public void test1(){
        boolean b = employees.stream()
                .anyMatch((e) -> e.getName() == "李四");
        System.out.println(b);
        long count = employees.stream().count();
        System.out.println(count);

        //年龄最大得人
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());

        //最少年龄是多少
        Optional<Integer> op = employees.stream()
                .map(Employee::getAge)
                .min(Double::compare);
        System.out.println(op.get());
    }
    //归约
    @Test
    public void test2(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8);
        Integer sum=list.stream()
                .reduce(0,(x,y)->x+y);
        System.out.println(sum);
    }

    /**
     *收集   Collectors
     * collect --将流转换为其他形式，接受一个Collector接口得实现，用于给Stream中元素汇总得方法
     */
    @Test
    public void test3(){
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        //年龄平均值
        Double collect1 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getAge));
        System.out.println(collect1);
    }
}
