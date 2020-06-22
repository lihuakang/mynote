package com.dl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。在任意点，在大
 * 多数 nThreads 线程会处于处理任务的活动状态。如果在所有线程处于活动状态时提交附加任务，
 * 则在有可用线程之前，附加任务将在队列中等待。如果在关闭前的执行期间由于失败而导致任何
 * 线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。在某个线程被显式地关闭之
 * 前，池中的线程将一直存在。
 */
public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                    System.out.println(Thread.currentThread().getName()+"正在被执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
