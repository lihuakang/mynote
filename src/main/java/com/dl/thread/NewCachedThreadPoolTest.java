package com.dl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池有四种方式
 *  ExecutorService是Java提供的用于管理线程池的类。该类的两个作用：控制线程数量和重用线程
 *  返回值都是ExecutorService
 *  Executors.newCacheThreadPool()是可缓存线程池，看线程池中有没有以前建立的线程，如果有直接用，没有创建新的，终止从缓存中移除那些超过60s没有使用的线程
 *
 */
public class NewCachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
