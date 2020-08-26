package com.dl.datastructure;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/19 15:32
 */
public class AVLTreeNode {
    private int data;
    private int height;
    private AVLTreeNode left;
    private AVLTreeNode right;
    public AVLTreeNode(int data){
        this.data=data;
        this.height=0;
        this.left=null;
        this.right=null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }
}
