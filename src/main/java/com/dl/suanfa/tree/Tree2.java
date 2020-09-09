package com.dl.suanfa.tree;

/**
 * 平衡二叉树（所有节点的左子树和右子树高度相差不超过1）
 * 判断是否是平衡二叉树
 */
public class Tree2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isBalanced(TreeNode root){
        return recur(root)!=-1;
    }
    private int recur(TreeNode root){
        if (root==null)return 0;
        int left=recur(root.left);
        if (left==-1)return -1;
        int right=recur(root.right);
        if (right==-1)return -1;
        return Math.abs(left-right)<2?1:-1;
    }
}
