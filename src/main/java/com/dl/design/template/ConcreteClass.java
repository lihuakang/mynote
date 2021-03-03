package com.dl.design.template;

public class ConcreteClass extends AbstractClass{
    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1被调用。。。");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2被调用。。。");
    }
}
