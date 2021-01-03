package com.dl.datastructure;

import java.util.Stack;

public class StackTest {
    private Object[] data=null;
    private int maxsize=0;
    private int top=-1;
    StackTest(){
        this(10);
    }

    public StackTest(int initialSize) {
    if (initialSize>=0){
        this.maxsize=initialSize;
        data=new Object[initialSize];
        top=-1;
    }else {
        throw new RuntimeException("初始化栈大小不能小于0");
    }
    }
    public boolean push(Object obj){
        if (top==maxsize-1){
            throw new RuntimeException("栈满");
        }else {
            data[++top]=obj;
            return true;
        }
    }

    public Object pop(){
        if (top==-1){
            throw new RuntimeException("栈已空");
        }else {
            return data[top--];
        }
    }
    public Object peek(){
        if (top==-1){
            throw new RuntimeException("栈一空");
        }else {
            return data[top];
        }
    }

    public static void main(String[] args) {
        StackTest stackTest=new StackTest();
        stackTest.push(11);
        stackTest.push(13);
        Object pop = stackTest.pop();
        System.out.println(pop);
        Object peek = stackTest.peek();
        System.out.println(peek);
        Object obj2=stackTest.pop();
        System.out.println(obj2);
//        stackTest.pop();
    }

    public static void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help=new Stack<Integer>();
        while (!stack.isEmpty()){
            int cur=stack.pop();
            while (!help.isEmpty() && help.peek()<cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}
