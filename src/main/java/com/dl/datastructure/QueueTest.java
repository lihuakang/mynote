package com.dl.datastructure;

public class QueueTest {
    private Object[] data=null;
    private int maxSize;//队列容量
    private int front;//队列头
    private int last;//队列尾
    QueueTest(){
        this(10);
    }

    public QueueTest(int initialSize) {
        if (initialSize>=0){
            this.maxSize=initialSize;
            data=new Object[initialSize];
            front=last=0;
        }else {
            throw new RuntimeException("队列长度不能小于0");
        }
    }
    //向队列中插入元素
    public boolean add(Object object){
        if (last==maxSize){
            throw new RuntimeException("队列已满");
        }else{
            data[last++]=object;
            return true;
        }
    }

    //出队列
    public Object pop(){
    if (front==last){
        throw new RuntimeException("栈为空");
    }else {
        return data[++front];
    }
    }
}
