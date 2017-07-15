package com.suanfa.recursion;

/**
 * 八皇后问题的一维求解
 * 定义一维数组,索引表示行号,值表示列号
 * Created by chang on 17/7/15.
 */
public class NQueenWithOneDimension {

    //N*N的方块
    static int max = 8;
    //索引代表行,值代表列
    static int[] queens = new int[max];

    /**
     * 检查要插入的点是否合法
     * @param row
     * @return boolean
     */
    static boolean check(int row) {

        //遍历已经放好的安全位置
        for (int i=0;i<row;i++) {
            //新加入的列是否和已有的列是同一列
            if (queens[i]==queens[row]) {
                return false;
            }
            //新加入的是否与原有的构成45度或135度角
            if (Math.abs(queens[i]-queens[row]) == Math.abs(i-row)) {
                return false;
            }
        }
        return true;
    }

    //从第row行开始尝试去找满足条件的位置,放入皇后
    static void solve(int row) {
        //递归退出条件,当row==max时
        if (row==max) {
            for (int i=0;i<max;i++) {
                //打印安全位置的行号和列号
                System.out.println("("+i+","+queens[i]+")");
            }
            System.out.println("----------------");
        } else {
            //按列遍历,按行递归
            for (int col=0;col<max;col++) {
                //先把第0行的值等于第0列,
                //如果这个位置是安全的, 则递归下一行,
                //一直找到8个安全位置后退出
                // (如果这一行不安全, 则循环重新给queens[row] = 1)
                queens[row] = col;
                //如果第0行是安全的, 则递归下一行
                if (check(row)) {
                    solve(row+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        solve(0);
    }
}
