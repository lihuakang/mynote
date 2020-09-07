package com.dl.ll;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/4 11:36
 */
public class Test3 {
    public static void main(String[] args) {
        new Person.AA().say();
        Person p=new Person();
        System.out.println(Person.AA.class.hashCode());
        System.out.println(new Person.AA().hashCode());
        System.out.println(new Person.AA().hashCode());
        new Person.AA().say();
    }
}
