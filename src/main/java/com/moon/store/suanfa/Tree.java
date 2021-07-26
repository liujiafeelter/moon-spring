package com.moon.store.suanfa;

/**
 * 遍历二叉树
 */
public class Tree {

    //前根遍历
    public void preOrder(BinaryTreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.getData() + "\t");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    //中根遍历
    public void midOrder(BinaryTreeNode root){
        if(root == null){
            return;
        }
        midOrder(root.getLeft());
        System.out.println(root.getData() + "\t");
        midOrder(root.getRight());
    }

    //后根遍历
    public void afterOrder(BinaryTreeNode root){
        if(root == null){
            return;
        }
        afterOrder(root.getLeft());
        afterOrder(root.getRight());
        System.out.println(root.getData() + "\t");
    }

}
class BinaryTreeNode {
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode() {}

    public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
