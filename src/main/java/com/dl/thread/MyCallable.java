package com.dl.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private String name;
    public MyCallable(String name){
        this.name=name;
    }
    @Override
    public Object call() throws Exception {
        return name;
    }
}
