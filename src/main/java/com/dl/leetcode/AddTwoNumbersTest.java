package com.dl.leetcode;

import org.junit.Test;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/1 14:35
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class AddTwoNumbersTest {
    public static void main(String[] args) {
        ListNode l1=new ListNode(2);
        ListNode l11=new ListNode(4);
        ListNode l21=new ListNode(3);
        l1.next=l11;
        l11.next=l21;
        ListNode l2=new ListNode(5);
        ListNode l12=new ListNode(6);
        ListNode l22=new ListNode(4);
        l2.next=l12;
        l12.next=l22;
        ListNode listNode = addTwoNumbers(l1, l2);
        while (l1!=null){
            System.out.print(l1.val);
            l1=l1.next;
        }
        System.out.println();
        while (l2!=null){
            System.out.print(l2.val);
            l2=l2.next;
        }
        System.out.println();
        while (listNode.next!=null){
            listNode=listNode.next;
            System.out.print(listNode.val);
        }

    }
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(){}
        public ListNode(int val) {
            this.val = val;
        }
    }
        public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
            ListNode dummy=new ListNode(0);
            int sum=0;//结果
            int more=0;//进位
            ListNode pre=dummy;
            while (l1!=null || l2!=null ||more >0){
                sum=((l1==null?0:l1.val )+ (l2==null?0:l2.val+more));
                more=sum/10;
                sum=sum%10;
                ListNode node=new ListNode(sum);
                pre.next=node;
                pre=node;
                l1=l1==null?null:l1.next;
                l2=l2==null?null:l2.next;
            }
            return dummy;
        }



}
