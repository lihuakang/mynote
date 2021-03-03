package com.dl.design.adapter;

public class Adapter implements Target {
    //直接关联被适配类
    private Adaptee adaptee;
    //可以通过构造函数传入具体需要被适配类的对象
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.SpecificRequest();
    }
}
