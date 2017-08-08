package com.suanfa.sort;

/**
 * 选择排序
 * 它的工作原理如下，首先在未排序序列中找到最小元素，
 * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找次小元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 如果某个元素位于正确的最终位置上，则它不会被移动。
 * T(n) = O(n^2)
 * Created by chang on 17/8/8.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] ints = {5, 3, 8, 1, 2, 4, 9};
        selectionSort(ints);
    }

    static void selectionSort(int[] ints) {
        for (int i=0;i<ints.length-1;i++) {
            //未排序序列中最小数据数组下标
            int minIdx = i;
            //在未排序元素中继续寻找最小元素，并保存其下标
            for (int j=i+1;j<ints.length;j++) {
                if (ints[minIdx]>ints[j]) {
                    minIdx = j;
                }
            }
            //将最小元素放到已排序序列的末尾
            swap(ints, i, minIdx);
        }
        System.out.printf("");
    }

    /**
     * 交换i,j的位置的元素
     * @param ints
     * @param i
     * @param j
     */
    private static void swap(int[] ints, int i, int j) {
        if (i==j) return;
        // 异或运算^, 相同为0, 不相同为1
        ints[i] = ints[i] ^ ints[j];
        ints[j] = ints[i] ^ ints[j];
        ints[i] = ints[i] ^ ints[j];
    }

}
