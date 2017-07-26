package com.suanfa.binaryTree;

/**
 * 线索二叉树
 * Created by chang on 17/7/24.
 */
public class ThreadedBinaryTree {

    static Node root;
    static Node preRoot;


    /**
     * 将二叉树线索化
     * @param root
     */
    static void inThread(Node root){
        if(root != null){
            inThread(root.left);  //线索化左孩子
            if(null == root.left){  //左孩子为空
                root.ltag = 1 ;  //将左孩子设置为线索
                root.left = preRoot;  //前驱
            }
            if(preRoot!=null&&null == preRoot.right){
                // pre节点的右孩子为空,
                // 则将pre节点的右孩子设置为当前节点(后继)
                preRoot.rtag = 1;
                preRoot.right = root;
            }
            preRoot = root ;
            inThread(root.right);  //线索化右孩子
        }
    }

    static void buildBryTree (int[] datas) {
        for (int data : datas) {
            insert(data);
        }
    }

    static void insert(int data) {
        root = insert(root, data);
    }
    static Node insert(Node root, int data) {
        if (root==null) {
            root = new Node(data);
        } else {
            if (data<root.data) {
                root.left = insert(root.left, data);
            } else if (data>root.data) {
                root.right = insert(root.right, data);
            }
        }
        System.out.println(root.data);
        return root;
    }





    static class Node {
        //数据域
        int data;
        //0代表该节点的左孩子;
        //1代表该节点的前驱或后继
        int ltag;
        Node left;
        int rtag;
        Node right;

        Node(int data) {
            this.data = data;
        }

        Node(int data, int ltag, Node left) {
            this(data);
            this.ltag = ltag;
            this.left = left;
        }

        Node(int data, int ltag, Node left, int rtag, Node right) {
            this(data, ltag, left);
            this.rtag = rtag;
            this.right = right;

        }
    }


    public static void main(String[] args) {
        int[] datas = {5, 3, 8, 1, 4, 6, 9};
        buildBryTree(datas);
        inThread(root);
        System.out.println();
    }
}
