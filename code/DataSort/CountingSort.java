package com.suanfa.sort;

/**
 * 计数排序<非比较的排序算法>
 * 将待排序的数组的值赋给一个临时数组的下标(临时数组的值存相同下标的个数)
 * 对临时数组遍历即可完成排序
 * T(n) = O(m + n)
 * Created by chang on 17/8/14.
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] ints = {5, 3, 8, 1, 2, 4, 9};
        bucketSort(ints);
    }

    static void bucketSort(int[] list) {
        //带排序数组的长度
        int lLen = list.length;

        //通过一次遍历找到数组的最大值
        for (int i=1;i<lLen;i++) {
            if (list[i-1]>list[i]) {
                swap1(list, i-1, i);
            }
        }
        int max = list[lLen-1];

        //创建一个数组(最大的下标为max)
        //初始化每个元素都为0
        int[] buckets = new int[max+1];

        for (int i : list) {
            //buckets的下标为list的值, buckets的值为下标的个数
            //(即list重复元素的个数)
            //则list则buckets中已经排好序了
            buckets[i]++;
        }

        //重新给list赋值时定义的下标
        int idx = 0;
        //遍历buckets
        for (int i=0;i<buckets.length;i++) {
            //如果有重复元素的情况下
            while (buckets[i]>0) {
                //buckets的下标重新赋值回list
                list[idx++] = i;
                //计数减1
                buckets[i]--;
            }
        }
        System.out.println();
    }

    private static void swap1(int[] ints, int i, int j) {
        //i等于j时,ints[i]==ints[j],第二条语句就会出问题,所以直接退出
        if (i==j) return;
        ints[i] = ints[i] + ints[j];
        ints[j] = ints[i] - ints[j];
        ints[i] = ints[i] - ints[j];
    }
}
