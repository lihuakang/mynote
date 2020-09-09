package com.dl.thread;

public class DaemonThread extends Thread {
    public void run(){
    for (int i=0;i<100;i++){
        System.out.println(currentThread().getName()+"执行了"+i);
    }
    }
    public static void main(String[] args) {
        DaemonThread daemonThread=new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"执行了"+i);
        }
    }
}
