package com.suanfa.recursion;

/**
 * 递归求1到m的和
 * Created by chang on 17/7/6.
 */
public class RecSum {

    static int getSum(int m) {
        if (m==1) {
            return 1;
        }
        return (m + getSum(m-1));
    }

    public static void main(String[] args) {
        System.out.println(getSum(100));
    }
}
