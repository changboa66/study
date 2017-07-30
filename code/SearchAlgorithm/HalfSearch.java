package com.suanfa.search;

/**
 * 折半查找的递归实现
 * Created by chang on 17/7/30.
 */
public class HalfSearch {


    public static void main(String[] args) {
        int[] ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(halfSearch(ints, 0, ints.length-1, 1));
    }

    static int halfSearch(int[] ints, int leftIdx, int rightIdx, int key) {
        //递归退出条件,left>right
        if (leftIdx>rightIdx) {
            return -1;
        }
        //数组的中间索引
        int midIdx = (leftIdx+rightIdx)>>1;
        //查找到了需要的值key, 退出
        if (key==ints[midIdx]) {
            return midIdx;
            //没查找到key, 则递归继续查找
            //要查找的数字小于中间位置的数字, 则搜索左半部分
        } else if (key<ints[midIdx]) {
            return halfSearch(ints, leftIdx, midIdx-1, key);
        } else {  //搜索右半部分
            return halfSearch(ints, midIdx+1, rightIdx, key);
        }
    }
}
