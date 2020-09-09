package com.dl.jianzhi;

/**
 * 在不改变原来数据结构的情况下，从尾到头打印链表
 *
 * 使用递归
 */
public class Day02 {
    public static void main(String[] args) {
        Node first=new Node(1);
        first.setnext(new Node(2)).setnext(new Node(3)).setnext(new Node(4)).setnext(new Node(5));
        revert(first);
    }

    private static void revert(Node node){
        if (node!=null){
            revert(node.next);
            System.out.println(node.value);
        }
    }
    private static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value=value;
        }
        public Node setnext(Node node){
            return this.next=node;
        }
    }
}
