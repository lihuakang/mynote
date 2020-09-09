package com.dl.suanfa;

/**
 * 给出一个数组，把数组分成两个随机大小的两部分，然后把两部分交换（原地交换，不借助其他容器）
 * eg：[a,b,c,d,e,f] 给出左边长度为4    交换后为[e,f,a,b,c,d]
 * 1 前四个字符进行逆序交换（双指针）
 * 2 后两个字符进行逆序交换（双指针）
 * 3 把整体进行逆序交换
 */
public class Test8 {
    public static void main(String[] args) {
    char[] chars={'a','b','c','d','e','f'};
    left(chars,4);
    right(chars,4);
    all(chars);
        System.out.println(chars);
    }


    public static void left(char[] arr,int a){
        int front=0;
        int rear=a-1;
        while (front<rear){
            char temp=arr[front];
            arr[front]=arr[rear];
            arr[rear]=temp;
            front++;
            rear--;
        }
//        right(arr,a);
        return;
    }
    public static void right(char[] arr,int a){
        int front=a;
        int rear=arr.length-1;
        while (front<rear){
            char temp=arr[front];
            arr[front]=arr[rear];
            arr[rear]=temp;
            front++;
            rear--;
        }
//        all(arr);
        return;
    }
    public static void all(char[] arr){
        int front=0;
        int rear=arr.length-1;
        while (front<rear){
            char temp=arr[front];
            arr[front]=arr[rear];
            arr[rear]=temp;
            front++;
            rear--;
        }
    }
}
