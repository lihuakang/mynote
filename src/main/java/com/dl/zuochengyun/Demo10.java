package com.dl.zuochengyun;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/10 16:52
 * 删除链表的中间节点和a/b处的节点
 * 如何根据链表的长度n，以及a与b的值决定该删除的节点是哪一个节点呢？根据如下方法：先计算double r=（（double） （a*n））/（（double） b）的值，然后r向上取整之后的整数值代表该删除的节点是第几个节点。
 * 下面举几个例子来验证一下。
 * 如果链表长度为7，a=5，b=7。r=（7*5）/7=5.0，向上取整后为5，所以应该删除第5个节点
 * 。如果链表长度为7，a=5，b=6。r=（7*5）/6=5.8333…，向上取整后为6，所以应该删除第6个节点。
 */
public class Demo10 {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value=data;
        }
    }
    public Node removeByRatio(Node head,int a,int b){
        if (a<1 || a>b){
            return head;
        }
        int n=0;
        Node cur=head;
        while (cur != null){
            n++;
            cur=cur.next;
        }
        n=(int)Math.ceil((double) a*n/(double)b);
        if (n == 1){
            head=head.next;
        }
        if (n > 1){
            cur=head;
            while (--n != 1){
                cur=cur.next;
            }
            cur.next=cur.next.next;
        }
        return head;
    }
}
