package com.suanfa.recursion;

/**
 * 八皇后问题最容易想到的用二维数组表示
 * boolean类型, true表示安全, false表示不安全
 * Created by chang on 17/7/13.
 */
public class NQueenWithTwoDimension {

    public static void main(String[] args) {
        //从第0行开始找满足条件的位置
        queen(0);
    }

    static int max = 8;
    //定义max*max的二维数组
    static boolean[][] safe = new boolean[max][max];


    //从第row行开始试图放入皇后
    static void queen(int row) {
        //递归退出条件,
        //当row>=max时(最后一次递归后,row正好等于max,此时退出)
        //打印放好位置互相不能攻击的八个皇后
        if (row==max) {
            for (int i=0;i<max;i++) {
                for (int j=0;j<max;j++) {
                    System.out.print(safe[i][j] + ",");
                }
                System.out.println();
            }
            System.out.println("=====================");
        } else {
            // 按列遍历,按行递归
            // (发散一样,此列满足则一直递归下一行;
            // 递归到底或者此列不满足时,将此列设置为false,尝试下一列(回溯))
            for (int col=0;col<max;col++) {
                //设置该行的列为true
                safe[row][col] = true;
                //如果设置的true是安全的,
                // 则一直递归到底,找到解
                if (isSafe(row, col)) {
                    queen(row+1);
                }
                //回溯(将该位置设置为false,继续将下一个位置设置为true进行尝试)
                safe[row][col] =false;
            }
        }
    }

    //要插入的位置是否安全
    static boolean isSafe(int row, int col) {
        //遍历
        for (int i=0;i<row;i++) {
            for (int j=0;j<max;j++) {
                //找到已经插入true的点
                if (safe[i][j]) {
                    //列相等
                    if (col==j) {
                        return false;
                    }
                    //45度或135度
                    if (Math.abs(i-row)==Math.abs(j-col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }




}
