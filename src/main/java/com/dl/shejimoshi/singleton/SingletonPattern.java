package com.dl.shejimoshi.singleton;

/**
 *   饿汉式单例
 *   初始化直接创建对象
 */
public class SingletonPattern {
    private static final SingletonPattern singletonPattern=new SingletonPattern();

    private SingletonPattern(){

    }

    public synchronized static SingletonPattern getInstance(){
        return singletonPattern;
    }
}
