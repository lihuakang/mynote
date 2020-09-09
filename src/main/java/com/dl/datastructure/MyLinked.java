package com.dl.datastructure;

public class MyLinked {
    Node first;
    int size;
    //在单链表末尾插入
    public void add(Object value){
        Node node=new Node(value);
        if(first==null){
            first=node;
        }else {
            Node stemp=first;
            while (stemp.next!=null){
                stemp=stemp.next;
            }
            stemp.setNext(node);
        }
        size++;
    }
    //在单链表中间插入
    public void add(Object value, int index) {
        Node newNode = new Node(value);
        //直接在末尾插入
        if (index > size - 1) {
            add(value);
        } else {
            Node pre = first;
            Node oldNode = first;
            for (int i = 0; i < index; i++) {
                //pre设置成插入位置的前一个结点
                if (i == index - 1) {
                    pre = oldNode;
                }
                //遍历
                oldNode = oldNode.getNext();
            }
            pre.setNext(newNode);
            //newNode为新插入节点，
            newNode.setNext(oldNode);
            size++;
        }
    }
    //取出指定编号位置的节点值
    public Object get(int index){
        Node temp=first;
        for(int i=0;i<index;i++){
            temp=temp.getNext();
        }
        return temp.getValue();
    }

    //删除元素
    public void delete(int index){
        //为空
        if(index>size-1){
            return;
        }
        //删除头节点
        if (index==0){
            first=first.getNext();
        }else {
            Node temp=first;
            for (int i=0;i<index-1;i++){
                //找到删除节点的前一个节点
                temp=temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }
    //删除值为多少的节点
    public void remove(Object obj){
        Node stemp=first;
        for(int i=0;i<size;i++){
            if(stemp.getNext().getValue().equals(obj)){
                stemp.setNext(stemp.getNext().getNext());
                break;
            }
            stemp=stemp.getNext();
        }
        size--;
    }

    //遍历节点
    public void getAll(){
        Node temp=first;
        for (int i=0;i<size;i++){
            System.out.println(temp.getValue());
            temp=temp.getNext();
        }
    }


//节点类
    class Node{
        private Node next;
        private Object value;

        public Node(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
        int length(){
            return size;
        }
    }

    public static void main(String[] args) {
       MyLinked myLinked=new MyLinked();
       myLinked.add(1);
       myLinked.add(3);
       myLinked.add(8);
       myLinked.remove(8);
       myLinked.remove(3);
       myLinked.getAll();
    }
}
