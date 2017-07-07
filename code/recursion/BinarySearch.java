package com.suanfa.recursion;

/**
 * 二分查找
 * 遮半查找
 * Created by chang on 17/7/6.
 */
public class BinarySearch {

    static int binarySearch(int[] array, int toSearch, int lowIdx, int highIdx) {

        // 递归退出条件<<highIdx<lowIdx || lowIdx>highIdx>>
        // 当低位的索引大于高位的索引
        // 或高位的索引大于低位的索引时退出
        if (highIdx < lowIdx || lowIdx > highIdx) {
            return -1;
        }
        // 求中间位置索引
        // <一般写法为(low+high)/2,
        // 但low+high有可能大于Integer.maxInt,
        // 所以写做(low+(high-low)/2)的形式
        // 移位运算符的优先级小于加号,所以必须括号>
        int midIdx = lowIdx + ((highIdx-lowIdx)>>1);
        int midNum = array[midIdx];
        //中间位置的数等于要查找的数,返回中间位置的索引值
        if (toSearch==midNum) {
            return midIdx;
        } else if (toSearch>midNum) {  //要查找的数大于中间位置的数,搜索后半段数组
            return binarySearch(array, toSearch, midIdx+1, highIdx);
        } else {  //要查找的数小于中间位置的数,搜索前半段数组
            return binarySearch(array, toSearch, lowIdx, midIdx-1);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 6, 7, 8, 9};
        System.out.println(binarySearch(array, 111, 0, 6));
    }
}
