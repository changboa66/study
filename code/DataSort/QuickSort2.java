package com.suanfa.sort;

/**
 * 快速排序2
 * 右指针j找比基准数小的，左指针i找比基准数大的，交换之。
 * T(n) = O(nlgn)
 * Created by chang on 17/8/13.
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27};
        quickSort2(arr, 0, 6);
    }

    static void quickSort2(int[] array, int startIdx, int endIdx) {
        if (startIdx>=endIdx) {
            return;
        }
        //基准元素
        int pivotKey = array[startIdx];
        //低位指针
        int low = startIdx;
        //高位指针
        int high = endIdx;

        //右指针找到一个比基准元素小的,
        //左指针找到一个比基准元素大的, 然后交换位置
        while (low<high) {
            //因为低位是从startIdx开始的, 所以比较的时候加上等号
            while (low<high && array[high]>=pivotKey) {
                high--;
            }
            while (low<high && array[low]<=pivotKey) {
                low++;
            }
            swap(array, low, high);
            System.out.println();
        }
        swap(array, startIdx, low);
        quickSort2(array, startIdx, low-1);
        quickSort2(array, low+1, endIdx);
        System.out.print("");
    }

    static void swap(int[] array, int i, int j) {
        if (i==j) return;
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }
}
