package com.suanfa.recursion;

/**
 * 递归求阶乘
 * Created by chang on 17/7/6.
 */
public class Factorial {

    static int getFactorical(int x) {

        //递归退出条件
        if (x==1) {
            return 1;
        }

        return x*getFactorical(x-1);
    }

    public static void main(String[] args) {
        System.out.println(getFactorical(1));
    }
}
