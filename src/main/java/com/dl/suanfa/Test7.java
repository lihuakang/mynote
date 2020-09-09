package com.dl.suanfa;

import java.util.ArrayList;
import java.util.List;

/**
 * 从尾到头反过来打印出每个结点的值。
 */
public class Test7 {
    public List print(ListNode head){
        List list=new ArrayList<>();
        ListNode temp=head;
        while (temp!=null){
            list.add(temp.val);
            temp=temp.next;
        }
        return list;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
        void add(int a){
            ListNode listNode=new ListNode(a);
            this.next=listNode;
        }
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(3);
        listNode.add(7);
        listNode.add(9);
        Test7 test7=new Test7();
        List print = test7.print(listNode);
        System.out.println(print.get(0));
        System.out.println(print.get(1));
    }
}
