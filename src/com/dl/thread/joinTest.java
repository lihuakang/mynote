package com.dl.thread;

public class joinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"被执行了");
            }
            }
        });
        thread1.start();
        //join之后，只有thread1执行完，才能执行主线程
//        thread1.join();
        System.out.println("你好");
    }
}
