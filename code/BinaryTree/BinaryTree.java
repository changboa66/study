package com.suanfa.binaryTree;


/**
 * 二叉树的创建和遍历
 * Created by chang on 17/7/23.
 */
public class BinaryTree {

    //中序遍历
    static void midSearchTree (Node node) {
        if (node.left==null && node.right==null) {
            System.out.println(node.data);
        } else if (node.left==null) {
            midSearchTree(node.right);
            System.out.println(node.data);
        } else if (node.right==null) {
            midSearchTree(node.left);
            System.out.println(node.data);
        } else {
            midSearchTree(node.left);
            System.out.println(node.data);
            midSearchTree(node.right);
        }
    }

    //前序遍历
    static void preSearchTree (Node node) {
        if (node.left==null && node.right==null) {
            System.out.println(node.data);
        } else if (node.left==null) {
            System.out.println(node.data);
            preSearchTree(node.right);
        } else if (node.right==null) {
            System.out.println(node.data);
            preSearchTree(node.left);
        } else {
            System.out.println(node.data);
            preSearchTree(node.left);
            preSearchTree(node.right);
        }
    }

    //后序遍历
    static void afterSearchTree (Node node) {
        if (node.left==null && node.right==null) {
            System.out.println(node.data);
        } else if (node.left==null) {
            afterSearchTree(node.right);
            System.out.println(node.data);
        } else if (node.right==null) {
            afterSearchTree(node.left);
            System.out.println(node.data);
        } else {
            afterSearchTree(node.left);
            afterSearchTree(node.right);
            System.out.println(node.data);
        }
    }


    //二叉树的遍历
    static void search(Node node) {
        if (node.left==null && node.right==null) {
            System.out.println(node.data);
            return;
        }

        //前序遍历
        //System.out.println(node.data);
        if (node.left!=null) {
            search(node.left);
        }

        //中序遍历
        //System.out.println(node.data);
        if (node.right!=null) {
            search(node.right);
        }
        //后序遍历
        System.out.println(node.data);

    }

    static Node root;

    static void buildTree(int[] datas) {
        for (int data : datas) {
            insert(data);
        }
    }

    static void insert(int data) {
        //每次新建的节点作为新的root传入
        root = insert(root, data);
    }

    /**
     * 1.若当前的二叉查找树为空，则插入的元素为根节点，
     * 2.若插入的元素值小于根节点值，则将元素插入到左子树中，
     * 3.若插入的元素值不小于根节点值，则将元素插入到右子树中。
     * @param root : 自变化的递归变量作参数(用来结束递归)
     * @param data
     * @return Node
     */
    static Node insert(Node root, int data) {
        if (null==root) {
            root = new Node(data);
        } else if (data<root.data) {
            root.left = insert(root.left, data);
        } else if (data>root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] datas = {5, 3, 1, 4, 8, 6, 9};
        buildTree(datas);
        System.out.println();
    }




    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
