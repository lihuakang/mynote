package com.dl.jianzhi;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/8/24 15:52
 */
public class Day05 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }
    public class Solution {
        public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
            TreeNode root=new TreeNode(pre[0]);//前序的第一个数定为根
            int len=pre.length;
            //当只有一个数的时候
            if(len==1){
                root.left=null;
                root.right=null;
                return root;
            }
            //找到中序中的根位置
            int rootval=root.val;
            int i;
            for(i=0;i<len;i++){
                if(rootval==in[i])
                    break;
            }
            //创建左子树
            if(i>0){
                int[] pr=new int[i];
                int[] ino=new int[i];
                for(int j=0;j<i;j++){
                    pr[j]=pre[j+1];
                }
                for(int j=0;j<i;j++){
                    ino[j]=in[j];
                }
                root.left=reConstructBinaryTree(pr,ino);
            }else{
                root.left=null;
            }
            //创建右子树
            if(len-i-1>0){
                int[] pr=new int[len-i-1];
                int[] ino=new int[len-i-1];
                for(int j=i+1;j<len;j++){
                    ino[j-i-1]=in[j];
                    pr[j-i-1]=pre[j];
                }
                root.right=reConstructBinaryTree(pr,ino);
            }else{
                root.right=null;
            }


            return root;
        }
    }

}
