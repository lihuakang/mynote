package com.dl.datastructure;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/19 15:35
 */
public class AVLTest {
    public static void main(String[] args) {
//    assert
    }

    //LL旋转
    public AVLTreeNode singleRotateLeft(AVLTreeNode x){
        AVLTreeNode w=x.getLeft();
        x.setLeft(w.getRight());
        w.setRight(x);
        x.setHeight(Math.max(Height(x.getLeft()),Height(x.getRight()))+1);
        w.setHeight(Math.max(Height(w.getLeft()),x.getHeight())+1);
        return w;
    }
    public int Height(AVLTreeNode root){
        if (root==null){
            return  0;
        }else {
            return root.getHeight();
        }
    }
}
