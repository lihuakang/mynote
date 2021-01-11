package com.dl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWait implements Runnable{
    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        try {
            lock.lock();
            try {
                condition.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }
    }
}
