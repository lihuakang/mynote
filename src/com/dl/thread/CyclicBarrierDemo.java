package com.dl.thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier循环屏障，适用于多线程同时开始的场景
 * 需要凑够多少个线程才能执行
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i=0;i<9;i++){
            Thread.sleep(1000);
            exe.execute(()->{
                try {
                    System.out.println(new Date()+"线程"+Thread.currentThread().getName()+"准备就绪");
                    //设置屏障点
                    cyclicBarrier.await();
                    System.out.println(new Date()+"线程"+Thread.currentThread().getName()+"开始运行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        exe.shutdown();
    }
}
