package com.suanfa.recursion;

/**
 * 欧几里得求两个数的最大公约数
 * a = 64, b = 40
 * 64/40=24
 * 40/24=16
 * 24/16=8
 * 16/8=0
 * 8/0(还没计算就退出)----此时退出,
 * 返回的8即是最大公约数
 * Created by chang on 17/7/6.
 */
public class Euclidean {

    static int getMaxCommDivisor(int a, int b) {
//        int x = Math.max(a, b);
//        int y = Math.min(a, b);
//        //递归退出条件
//        if (x%y==0) {
//            return y;
//        }
//        return getMaxCommDivisor(y, x%y);

        //递归退出条件
        if (b == 0) {
            return a;
        }

        // 第一次调用时如果a>b,则直接递归
        // 如果a<b,则会多一次递归调用,使a.b交换交换,
        // 大数变成第一个参数,小数变成第二个参数(a%b=a)
        return getMaxCommDivisor(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(getMaxCommDivisor(4,6));
    }
}
