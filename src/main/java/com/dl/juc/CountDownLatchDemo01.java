package com.dl.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo01 extends Thread {
    static CountDownLatch countDownLatch=new CountDownLatch(1);//定义令牌数

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new CountDownLatchDemo01().start();
        }
        countDownLatch.countDown();//减一
    }

    @Override
    public void run() {
        try{
            countDownLatch.await();//阻塞
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("ThreadName"+Thread.currentThread().getName());
    }
}
