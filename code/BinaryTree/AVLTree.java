package com.suanfa.binaryTree;

/**
 * Created by chang on 17/8/2.
 */
public class AVLTree {


    public static void main(String[] args) {
        insertAVL(3);
        insertAVL(1);
        insertAVL(2);
        System.out.println();
    }


    //平衡因子的值
    static int BF_0 = 0;
    static int BF_1 = 1;
    static int BF_NEG_1 = -1;
    static int BF_2 = 2;
    static int BF_NEG_2 = -2;

    static AVLNode root;
    static void insertAVL(int data) {
        root = insertAVL(root, data);
    }
    static AVLNode insertAVL(AVLNode root, int data) {
        //如果为空树或递归到叶节点下的左右空节点,则生成节点
        if (null==root) {
            root = new AVLNode(data, BF_0);
        } else {
            if (data<root.data) {
                root.left =  insertAVL(root.left, data);
                //递归到最后是叶节点,叶节点的left = new Node();
                // 从而叶节点变成旧的叶节点,新的叶节点是旧叶节点的left
                //然后下面的if开始从旧叶节点往回递归旧叶->旧叶的双亲->双亲的双亲...,
                // 直到if成立,旋转
                //即:root节点的左子树的高度-右子树的高度=平衡因子大于等于2时(实际只能等于2)
                if (height(root.left)-height(root.right)>=BF_2) {
                    if (data>root.left.data) {
                        root = rotateLeftRight(root);
                    } else {
                        root = rotateRight(root);
                    }
                }
            } else {
                root.right = insertAVL(root.right, data);
                if (height(root.left)-height(root.right)<=BF_NEG_2) {
                    if (data<root.right.data) {
                        root = rotateRightLeft(root);
                    } else {
                        root = rotateLeft(root);
                    }
                }
            }
        }
        return root;
    }


    /**
     * 一:根节点的左子树插入左节点
     *         3
     *       /      右旋(3)
     *      2       ------->             2
     *     /                            / \
     *    1  (插入新节点1导致3不平衡)    1   3
     *---------------------------------------------
     * 二:根节点的左子树的左子树插入左节点
     *           4
     *         /  \
     *       3     5                      4
     *      /         右旋(3)            / \
     *    2           ------>          2   5
     *   /                            / \
     *  1                            1   3
     *  (插入新节点1导致3和4不平衡,最小失衡子树为3,正好与递归完成后的从叶到根节点往回回溯对上,右旋3后4也变平衡)
     *---------------------------------------------
     * 左左情况:左子树或左子树的左子树插入新节点(LL)
     * 向右旋转:使根node变成node.left的右子树(node.left变成新的根节点)
     * @param root
     * @return
     */
    static AVLNode rotateRight (AVLNode root) {
        AVLNode top = root.left;
        root.left = top.right;
        top.right = root;
        top.balanceFactor = height(top.left) - height(top.right);
        root.balanceFactor = height(root.left) - height(root.right);
        return top;
    }



    /**
     * 一:根节点的右子树插入右节点
     *  1
     *   \         左旋(1)
     *    2       ------->                  2
     *     \                               / \
     *      3    (插入新节点3导致1不平衡)   1   3
     *---------------------------------------------
     * 二:根节点的右子树的右子树插入右节点
     *       2
     *     /  \
     *    1    3        左旋(3)                 2
     *          \      ------->               / \
     *           4                           1   4
     *            \                             / \
     *             5                           3   5
     *  (插入新节点5导致3和2不平衡,最小不平衡子树为3,正好与从叶子到根节点往回回溯对应上,左旋3后2也变平衡,结束)
     *-------------------------------------------------
     * 右右情况:右子树或右子树的右子树插入新节点
     * 向左旋转:使根节点node变成node.rihgt的左子树(node.right变成新的根节点)
     * @param root
     * @return
     */
    static AVLNode rotateLeft (AVLNode root) {
        //新的根节点
        AVLNode top = root.right;
        //
        root.right = top.left;
        top.left = root;
        top.balanceFactor = height(top.left) - height(top.right);
        root.balanceFactor = height(root.left) - height(root.right);
        return top;
    }



    /**
     * 一:根节点的左子树插入右节点
     *      1                                 1
     *     /        左旋(3)                  /     右旋(1)
     *    3         ------>                2      ------>       2
     *     \                              /                    / \
     *      2    (插入新节点2导致1不平衡)  3                    1   3
     *-------------------------------------------------------------------
     * 二:根节点的左子树的右子树插入左节点
     *           5                               5
     *          / \                             / \
     *         2   6                           4   6                 4
     *        / \           左旋(2)           /          右旋(5)     / \
     *       1   4         ------->         2            ------>   2   5
     *          /                          / \                    / \   \
     *         3   (插入新节点3导致5失衡)   1   3                  1   3   6
     *--------------------------------------------------------------------
     * 三:根节点的左子树的右子树插入右节点
     *           5                                5
     *         /  \                              / \
     *       2     6                            3   6                  3
     *      / \          左旋(2)               / \         右旋(5)     / \
     *     1   3         ------>             2   4        ------->   2   5
     *          \                           /                       /   / \
     *           4   (插入新节点4导致5失衡)  1                       1   4   6
     *----------------------------------------------------------------------
     * 左右情况:左子树的右子树的节点处插入新节点(LR)
     * 先向左后向右旋转
     * @param root
     * @return
     */
    static AVLNode rotateLeftRight (AVLNode root) {
        root.left = rotateLeft(root.left);
        root = rotateRight(root);
        return root;
    }




    /**
     * 一:根节点的右子树插入左节点
     *  1                              1
     *   \         右旋(3)              \      左旋(1)
     *    3       ------->              2      ------>        2
     *   /                               \                   / \
     *  2       (插入新节点2导致1不平衡)    3                 1   3
     *-------------------------------------------------------------------
     * 二:根节点的右子树的左子树插入左节点
     *       2                           2
     *      / \                         / \
     *     1   5                       1   4                      4
     *        / \           右旋(5)       / \         左旋(2)     / \
     *       4   6         ------->     3   5         ------>   2   5
     *      /                                \                 / \   \
     *     3        (插入新节点3)              6               1   3   6
     *--------------------------------------------------------------------
     * 三:根节点的右子树的左子树插入右节点
     *       2                           2
     *      / \                         / \
     *     1   5                       1   3                      3
     *        / \           右旋(5)         \         左旋(2)     / \
     *       3   6         ------->         5         ------>   2   5
     *        \                            / \                 /   / \
     *         4      (插入新节点4)        4   6               1   4   6
     *----------------------------------------------------------------------
     * 右左情况
     * 先向右后向左旋转
     * @param root
     * @return
     */
    static AVLNode rotateRightLeft (AVLNode root) {
        root.right = rotateRight(root.right);
        root = rotateLeft(root);
        return root;
    }




    /**
     * 树高度
     */
    static int height(AVLNode root) {
        //空节点的高度看做0
        if (null==root) {
            return(0);
        }
        //每一层递归都会加1,即树高加1,直到叶子节点
        return Math.max(height(root.left)+1, height(root.right)+1);
    }

    static class AVLNode {
        //数据域
        int data;
        //平衡因子
        //代码中暂时没有用到
        int balanceFactor;
        //左孩子
        AVLNode left;
        //右孩子
        AVLNode right;

        AVLNode (int data, int balanceFactor) {
            this.data = data;
            this.balanceFactor = balanceFactor;
        }
        AVLNode (int data, int balanceFactor, AVLNode left, AVLNode right) {
            this(data, balanceFactor);
            this.left = left;
            this.right = right;
        }
    }
}
