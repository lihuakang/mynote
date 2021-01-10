package com.dl.datastructure;

public class LinkedStack {

    public class Node{
        public Node prev;
        public Object data;
        public Node next;

        public Node(Node prev, Object data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
        public Object getData(){
            return data;
        }
    }

    public class LinkedListStack{
        private int size;
        private Node tail;
        public LinkedListStack(){
            this.tail=null;
        }
        //入栈
        public boolean push(Object data){
            Node newNode=new Node(tail,data,null);
            if (size>0){
                tail.next=newNode;
            }
            tail=newNode;
            size++;
            return true;
        }
    }
}
