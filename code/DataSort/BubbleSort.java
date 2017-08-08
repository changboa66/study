package com.suanfa.sort;

/**
 * 冒泡排序算法
 * Created by chang on 17/8/7.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] ints = {5, 3, 8, 1, 2, 4, 9};
        bubbleSort(ints);
    }

    static void bubbleSort(int[] ints) {
        //外循环
        //比较多少趟
        for (int i=0;i<ints.length;i++) {
            // 内循环
            // 每走一趟比较的次数,每次都从第0个元素开始比较,
            // 如果比后面的元素大则交换位置,第一趟下来后最大的元素沉底排到了最后位置
            // 第二趟下来次大元素排在了倒数第二的位置....
            for (int j=0;j<ints.length-i-1;j++) {
                if (ints[j]>ints[j+1]) {
                    swap(ints, j, j+1);
                }
            }
        }
        System.out.println();
    }

    /**
     * 位运算
     * 不引进新的变量元素交换
     * @param ints 需要交换的数组
     * @param i 交换的索引
     * @param j 交换的索引
     */
    private static void swap(int[] ints, int i, int j) {
        //当i等于j时数组的位运算会出错,所以直接退出即可
	//因为第一次异或运算后,a[i]==a[j]==0,
	//可以把a[i],a[j]赋值给临时变量x,y解决此问题
	if (i==j) {
            return;
        }
	// 异或运算^, 相同为0, 不相同为1
        ints[i] = ints[i] ^ ints[j];
        ints[j] = ints[i] ^ ints[j];
        ints[i] = ints[i] ^ ints[j];
    }

    /**
     * 直接计算
     * 不引进新的变量元素交换
     * @param ints 需要交换的数组
     * @param i 交换的索引
     * @param j 交换的索引
     */
    private static void swap1(int[] ints, int i, int j) {
        ints[i] = ints[i] + ints[j];
        ints[j] = ints[i] - ints[j];
        ints[i] = ints[i] - ints[j];
    }


    /**
     * 元素交换
     * @param ints 需要交换的数组
     * @param i 交换的索引
     * @param j 交换的索引
     */
    private static void swap2(int[] ints, int i, int j) {
        int temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }

}
