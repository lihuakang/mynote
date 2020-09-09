package com.dl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程化的线程池，它只会唯一的工作线程来执行任务，报这个所有的任务按照指定顺序进行
 * 这个线程池只有一个线程
 */
public class NewSingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService singleThread= Executors.newSingleThreadExecutor();

        for(int i=0;i<10;i++){
            final int index=i;
            singleThread.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(Thread.currentThread().getName()+"被执行了");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
