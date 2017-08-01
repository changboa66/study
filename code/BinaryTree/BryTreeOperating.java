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

    /**
     * 二叉查找树的删除
     * @param root
     * @param toDelete
     * @return root
     */
    static Node deletion(Node root, Node toDelete) {
        //如果是空树,直接返回
        if (null==root) {
            return null;
        }
        //递归定位到需要删除的节点
        if (toDelete.data<root.data) {
            root.left = deletion(root.left, toDelete);
        } else if (toDelete.data>root.data) {
            root.right = deletion(root.right, toDelete);
        } else {  //root.data==toDelete.data(递归退出条件)
            //如果要删除的root节点只有左子树或只有右子树或者root是叶子节点时,
            //将root的左子树或右子树(叶子节点时返回空)返回,
            //连接到上一次递归的上面的root.left=...root.right=...
            if (root.left==null) {
                return root.right;
            } else if (root.right==null) {
                return root.left;
            } else {    //root有左右子树的情况
                //找到要删除的root节点的左子树的最右节点(中序遍历时的紧邻root的前一个节点)
                //将此节点的data赋给root节点, 然后删除此节点即可
                Node temp = root.left;
                //迭代找到root的左子树的最右节点(最右节点的右节点必然为空,左节点不一定为空)
                while (temp.right!=null) {
                    temp = temp.right;
                }
                root.data = temp.data;
                //因为temp节点不一定是叶子节点,
                //有可能temp还有左子树
                //将root.left整体看做一棵树,递归删除temp
                root.left = deletion(root.left, temp);
            }
        }
        return root;
    }


    public static void main(String[] args) {
        int[] datas = {5, 3, 1, 4, 8, 6, 9};
        BinaryTree.buildTree(datas);
        root = BinaryTree.root;
        doubleTree(root);
        System.out.println();
    }
}
