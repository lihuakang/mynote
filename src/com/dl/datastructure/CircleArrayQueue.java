package com.dl.datastructure;

public class CircleArrayQueue {
    private int maxsize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int initsize){
        this.maxsize=initsize;
        arr=new int[maxsize];

    }

    //判断队列是否满了
    public boolean isFull(){
        return (rear+1)%maxsize==front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void add(int n){
        if (isFull()){
            System.out.println("队列满了");
            return;
        }else {
            arr[rear]=n;
            rear=(rear+1)%maxsize;
        }
    }

    //出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        int value=arr[front];
        front=(front+1)%maxsize;
        return value;
    }

    //显示所有数据
    public void show(){
        if (isEmpty()){
            throw new RuntimeException("队列一空");
        }
        for (int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxsize,arr[i%maxsize]);
        }
    }

    public int size(){
        return (rear+maxsize-front)%maxsize;
    }

}
