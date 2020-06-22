package com.dl.datastructure;

public class TwoWayLinkedList {
    private Node head;//链表头
    private Node tail;//链表尾
    private int length;//链表长度

    private class Node{
        private Object data;
        private Node next;
        private Node prev;
        public Node(Object object){
            this.data=object;
        }
    }
    public TwoWayLinkedList(){
        length=0;
        head=null;
        tail=null;

    }
    //头插
    public void addHead(Object value){
        Node newNode=new Node(value);
        if (length==0){
            head=newNode;
            tail=newNode;
            length++;
        }else {
            //头插法
            head.prev=newNode;
            newNode.next=head;
            head=newNode;
            length++;
        }
    }

    //尾插
    public void addTail(Object value){
        Node newNode=new Node(value);
        if (length==0){
            head=newNode;
            tail=newNode;
            length++;
        }else {
            newNode.prev=tail;
            tail.next=newNode;
            tail=newNode;
            length++;
        }
    }

    //删除链表头节点
    public Node deleteHead(){
        Node temp=head;
        if (length!=0){
            head=head.next;
            head.prev=null;
            length--;
            return temp;
        }else {
            return null;
        }
    }

    //删除尾节点
    public Node deleteTail(){
        Node temp=tail;
        if (length!=0){
            tail=tail.prev;
            tail.next=null;
            length--;
            return temp;
        }else {
            return null;
        }
    }


}
