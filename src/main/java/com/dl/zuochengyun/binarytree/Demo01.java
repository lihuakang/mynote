package com.dl.zuochengyun.binarytree;

import org.junit.Test;

import java.util.Stack;

public class Demo01 {
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value=data;
        }
    }

    /**
     * 先序遍历
     * @param head
     */
    public void preOrderRecur(Node head){
        if (head==null){
            return;
        }
        System.out.println(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 非递归实现先序遍历  根左右
     *  1 申请一个栈 stack。然后将头节点压入栈
     *  2 从stack中弹出栈顶元素，记为cur，然后打印cur的值，再将节点cur的右孩子节点压入栈，最后再将cur的左孩子压入栈。
     *  3 不断重复步骤2，直到stack为空 过程结束
     */
    public void preOrderRecur2(Node head){
        System.out.println("preOrder: ");
        if (head==null){
            return;
        }
        if (head!=null) {
            Stack<Node> stack = new Stack();
            stack.add(head);
            while (!stack.isEmpty()){
                head=stack.pop();
                System.out.println(head.value+" ");
                if (head.right!=null){
                    stack.push(head.right);
                }
                if (head.left!=null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }
    //验证非递归 先序遍历
    @Test
    public void testPreOrder(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node1.left=node2;
        node1.right=node3;
        preOrderRecur2(node1);
    }


    /**
     *  4 2 5 1 6 3 7
     * 非递归实现 中序遍历
     *  1 申请一个栈stack，cur=head
     *  2 先把cur节点压入栈，对以cur为头节点的整颗子树来说，一次把左边界压入栈，不停的cur=cur.left，然后重复步骤二
     *  3 不断重复步骤二，直到发现cur为空，此时从stack中弹出一个节点，记为node，并让cur=node.right,重复步骤二
     *  4 当cur为空，并且stack为空时停止整个步骤
     *  弹出时打印
     */
    public void inOrderUnRecur(Node head){
        System.out.println("in-order: ");
        if (head!=null){
            Stack<Node> stack=new Stack<>();
            while (!stack.isEmpty() || head!=null){
                if (head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head=stack.pop();
                    System.out.print(head.value+" ");
                    head=head.right;
                }
            }
        }
    }
    //验证非递归 中序遍历
    @Test
    public void testInOrder(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node1.left=node2;
        node1.right=node3;
        inOrderUnRecur(node1);//4 2 5 1 6 3 7
    }


    /**
     * 二叉树 后序遍历  左 右 根
     * 用两个栈实现
     *  1 申请一个栈记为s1，然后将头节点head压入栈s1
     *  2 从s1中弹出的节点记为cur，然后依次将cur的左节点和右节点压入s1
     *  3 在整个过程中，每一个从s1弹出的节点都放进s2
     *  4 不断重复步骤2和步骤3，直到s1为空，过程停止
     *  5 从s2中依次弹出节点并打印，打印顺序就是后序遍历顺序。
     */
    public void posOrderUnOrderRecur1(Node head){
        System.out.println("pos-order:");
        if(head!=null){
            Stack<Node> s1=new Stack<Node>();
            Stack<Node> s2=new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()){
                head=s1.pop();
                s2.push(head);
                if (head.left!=null){
                    s1.push(head.left);
                }
                if (head.right!=null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value+" ");
            }
        }
    }
    //验证非递归 后序遍历
    @Test
    public void testPosOrder(){
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node1.left=node2;
        node1.right=node3;
        posOrderUnOrderRecur1(node1);//4 5 2 6 7 3 1
    }

}
