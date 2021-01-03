package com.dl.linked;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/26 11:24
 * 分别实现两个函数，一个可以删除单链表中倒数第K个节点，另一个可以删除双链表中倒数第K个节点。
 * 如果链表长度为N，时间复杂度达到O（N），额外空间复杂度达到O（1）。
 */
public class Demo02 {
    public class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value=value;
        }
    }
    public Node removeLastKthNode(Node head,int lastKth){
        if (head==null || lastKth<1){
            return head;
        }
        Node cur=head;
        while (cur!=null){
            lastKth--;
            cur=cur.next;
        }
        if (lastKth==0){
            head=head.next;
        }
        if (lastKth<0){
            cur=head;
            while (++lastKth!=0){
                cur=cur.next;
            }
            cur.next=cur.next.next;
        }
        return head;
    }
    public class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            this.value=data;
        }
    }
    public DoubleNode removeLastKthNode(DoubleNode head,int lastKth){
        if (head==null|| lastKth<1){
            return head;
        }
        DoubleNode cur=head;
        while (cur!=null){
            lastKth--;
            cur=cur.next;
        }
        if (lastKth==0){
            head=head.next;
            head.last=null;
        }
        if (lastKth<0){
            cur=head;
            while (++lastKth!=0){
                cur=cur.next;
            }
            DoubleNode newNext=cur.next.next;
            cur.next=newNext;
            if (newNext!=null){
                newNext.last=cur;
            }
        }
        return head;
    }
}
