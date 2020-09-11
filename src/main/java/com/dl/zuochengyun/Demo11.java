package com.dl.zuochengyun;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/10 17:01
 * 分别实现反转单向链表和反转双向链表的函数。
 * 【要求】如果链表长度为N，时间复杂度要求为O（N），额外空间复杂度要求为O（1）。
 */
public class Demo11 {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }
    //单向链表反转
    public Node reverseList(Node head){
        Node pre=null;
        Node next=null;
        while (head != null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    public class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int value){
            this.value=value;
        }
    }
    //双向链表反转
    public DoubleNode reverseList(DoubleNode head){
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head != null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }
}
