package com.suanfa.search;

/**
 * 插值查找(与二分查找一样,只是改变midIdx的值)
 * <<<迭代实现>>>
 * 时间复杂度也是O(logn)，但对于表长较大，而关键字分布又比较均匀的查找表来说，
 * 插值查找算法的平均性能比折半查找要好得多。
 * 反之，数组中如果分布类似{0,1,2,2000,2001,......,999998,999999}这种极端
 * 不均匀的数据，用插值查找不是合适的选择。
 * Created by chang on 17/7/31.
 */
public class InsertSearch {

    static int insertSearch(int[] ints, int leftIdx, int rightIdx, int key) {
        int midIdx;
        while (leftIdx<=rightIdx) {
            //必须把(rightIdx-leftIdx)写在前面, 否则后面的分数有可能小于1, 最后得到结果0
            midIdx = leftIdx + (rightIdx-leftIdx)*(key-ints[leftIdx])/(ints[rightIdx]-ints[leftIdx]);
            if (key>ints[midIdx]) {
                rightIdx = midIdx + 1;
            } else if (key<ints[midIdx]) {
                leftIdx = midIdx - 1;
            } else {
                return midIdx;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7 ,8, 9};
        System.out.println(insertSearch(ints, 0, 8, 7));
    }
}
