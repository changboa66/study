package com.suanfa.recursion;

/**
 * 斐波那契数列
 * 1, 1, 2, 3, 5, 8, 13, 21
 * Created by chang on 17/7/6.
 */
public class Fibonacci {

    static int getFib(int m) {
        //递归退出条件
        if (m==0 || m==1) {
            return 1;
        }
        return getFib(m-1) + getFib(m-2);
    }

    public static void main(String[] args) {
        System.out.println(getFib(7));
    }
}
