package com.dl.suanfa;

import javax.swing.*;

public class Test3 {
    public Node reverse(Node head){
        if (head==null||head.next==null){
            return head;
        }
        Node temp=head.next;
        Node newHead=reverse(head.next);
        temp.next=head;
        head.next=null;
        return newHead;
    }


    class Node{
        public Node next;
        public int value;
        public Node(int value){
            this.value=value;
        }
    }
}
