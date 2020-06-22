package com.dl.datastructure;

import java.util.Stack;

/**
 * 二叉排序树
 */
public class TreeNodeTest {
    public class TreeNode {
        public Integer data;
        public TreeNode left;
        public TreeNode right;
        //该节点的父节点
        public TreeNode parent;
        public TreeNode(int data){
            this.data=data;
        }
    }
    public TreeNode root;
    public long size;
    //二叉排序树的插入
    public Boolean addTreeNode(Integer data){
        if (root==null){
            root=new TreeNode(data);
            System.out.println("数据成功插入");
            return true;
        }
        TreeNode newTreeNode=new TreeNode(data);
        TreeNode currentNode=root;
        TreeNode parentNode;
        while (true){
            parentNode=currentNode;//保存父节点
            //插入节点比父节点小
            if (currentNode.data>data){
                currentNode=currentNode.left;
                if (null==currentNode){
                    parentNode.left=newTreeNode;
                    newTreeNode.parent=parentNode;
                    System.out.println("成功插入");
                    size++;
                    return true;
                }
            }else if (currentNode.data<data){
                currentNode=currentNode.right;
                if (null==currentNode){
                    parentNode.right=newTreeNode;
                    newTreeNode.parent=parentNode;
                    System.out.println("插入成功");
                    size++;
                    return true;
                }
            }else {
                System.out.println("插入数据与节点相等");
                return false;
            }
        }
    }
    //查找数据
    public TreeNode findTreeNode(Integer data){
        if (null==root){
            return null;
        }
        TreeNode current=root;
        while (current!=null){
            if (current.data>data){
                current=current.left;
            }else if(current.data<data){
                current=current.right;
            }else {
                return current;
            }
        }
        return null;
    }
    /**
     * 前序遍历,根左右
     */
    public static void preOrderMethodTwo(TreeNode treeNode){
        if (null!=treeNode){
            Stack<TreeNode> stack=new Stack<TreeNode>();
            stack.push(treeNode);
            while (!stack.isEmpty()){
                TreeNode tempNode=stack.pop();
                System.out.println(tempNode.data+"  ");
                if (null!=tempNode.right){
                    stack.push(tempNode.right);
                }
                if (null!=tempNode.left){
                    stack.push(tempNode.left);
                }
            }
        }
    }

    /**
     * 中序遍历，左根右
     */
    public void  medOrderMethodOne(TreeNode treeNode){
        if (null!=treeNode){
            if (null!=treeNode.left){
                medOrderMethodOne(treeNode.left);
            }
            System.out.println(treeNode.data+"  ");
            if (null!=treeNode.right){
                medOrderMethodOne(treeNode.right);
            }
        }
    }
    /**
     * 中序遍历方法二
     */
    public void medOrderMethodTwo(TreeNode treeNode){
        if (null!=treeNode){
            Stack<TreeNode> stack=new Stack<>();
            TreeNode current=treeNode;
        while (current!=null||!stack.isEmpty()){
            while (current!=null){
                stack.push(current);
                current=current.left;
            }
            if (!stack.isEmpty()){
                current=stack.pop();
                System.out.println(current.data+"  ");
                current=current.right;
            }
        }
        }
    }
    /**
     * 递归实现后序遍历
     * @author linbingwen
     * @since  2015年8月29日
     * @param treeNode
     */
    public static void postOrderMethodOne(TreeNode treeNode){
        if (null != treeNode) {
            if (null != treeNode.left) {
                postOrderMethodOne(treeNode.left);
            }
            if (null != treeNode.right) {
                postOrderMethodOne(treeNode.right);
            }
            System.out.print(treeNode.data + "  ");
        }

    }


    //二叉排序树的删除：三种情况
    //没有子节点，只需要更改父节点的指针
    //有一个子节点，让子节点替代自己
    //有两个子节点，替换节点为左子树中最大的，或者右子树中最小的

}
