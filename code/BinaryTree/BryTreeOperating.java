package com.suanfa.binaryTree;

import com.suanfa.binaryTree.BinaryTree.Node;

/**
 * 二叉搜索树的各种操作
 * Created by chang on 17/7/26.
 */
public class BryTreeOperating {

    // 树的根节点
    static Node root;

    /**
     * 查找当前树是否包含某个节点值
     * @param data
     * @return
     */
    static boolean lookup(int data) {
        return lookup(root, data);
    }
    static boolean lookup(Node root, int data) {
        //当节点为空时,直接返回false
        if (null==root) {
            return(false);
        }
        //当节点等于当前节点的值, 返回true
        if (data==root.data) {
            return(true);
        }

        // 当要查找的值小于当前节点的data域时,
        // 则搜索当前节点的左孩子, 否则搜索右孩子
        if (data<root.data) {
            return lookup(root.left, data);
        } else {
            return lookup(root.right, data);
        }
    }

    /**
     * 树的前序/中序/后序遍历算法(深度优先算法)
     * @param root
     */
    static void traversal(Node root) {
        if (null==root) {
            return;
        }
        System.out.println(root.data);  //前序
        traversal(root.left);
        // System.out.println(root.data);  //中序
        traversal(root.right);
        //System.out.println(root.data);  //后序
    }

    /**
     * 二叉查找树的节点个数
     * @param root
     */
    static int sumNode (Node root) {
        if (null==root) {
            return(0);
        }
        return (1+sumNode(root.left)+sumNode(root.right));
    }

    static int maxDeep(Node root) {
        if (null==root) {
            return(0);
        }
        return Math.max(maxDeep(root.left)+1, maxDeep(root.right)+1);
    }

    /**
     * 查找树的最左孩子节点的值
     * @param root
     */
    static void minValue(Node root) {
        if (null==root.left) {
            System.out.println(root.data);
            return;
        }
        minValue(root.left);
    }

    /**
     * 遍历二叉树,求解是否有一条路径的值的总和等于sum
     * @param root
     * @param sum
     * @return
     */
    static boolean hasPathSum(Node root, int sum) {
        if (null==root) {
            return sum==0;
        }
        int subSum = sum - root.data;
        //左孩子或者右孩子
        return (hasPathSum(root.left, subSum) || hasPathSum(root.right, subSum));
    }

    /**
     * 复制每一个节点到左孩子
     * 相当于后序遍历
     * @param root
     */
    static void doubleTree (Node root) {
        if (null==root) {
            return;
        }
        doubleTree(root.left);
        doubleTree(root.right);
        //未复制前root的left节点
        Node oldLeft = root.left;
        //新建要加入的节点
        Node newNode = new Node(root.data);
        //新节点的指针指向老left节点
        newNode.left = oldLeft;
        //root指向新节点
        root.left = newNode;
    }

    /**
     * 判断两个树是否完全相等
     * @param root
     * @param root1
     */
    static boolean sameTree(Node root, Node root1) {
        //如果都是空树则相同
        if (root==null && root1==null) {
            return true;
        }
        //如果都为非空,则判断当前节点是否相同
        //当前节点的左右孩子是否相同
        //以下代码可简化为
        //return (root!=null
        //          && root1!=null
        //          && root.data==root1.data
        //          && sameTree(root.left, root1.left)
        //          && sameTree(root.right, root1.right))
        if (root!=null && root1!=null) {
            return (root.data==root1.data
                    && sameTree(root.left, root1.left)
                    && sameTree(root.right, root1.right));
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        int[] datas = {5, 3, 1, 4, 8, 6, 9};
        BinaryTree.buildTree(datas);
        root = BinaryTree.root;
        doubleTree(root);
        System.out.println();
    }
}
