package com.dl.suanfa.tree;

import javax.swing.tree.TreeNode;

/**
 * 一棵树要么是空树，要么有两个指针，树是一种递归结构
 * 求一棵树的高度
 */
public class Tree1 {
    public class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
    public int maxDepth(TreeNode root){
        if (root==null){
            return 0;
        }
        //递归调用左儿子的深度和右儿子的深度，深度加1
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
