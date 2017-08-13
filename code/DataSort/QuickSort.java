package com.suanfa.sort;

/**
 * 快速排序
 * 1）  选择一个基准元素,通常选择第一个元素
 * 1.1) 找到基准元素后, <空出基准元素的位置>,<从后向前>遍历找到比基准元素小的填入到空位置
 *      此时,后面有一个位置空出来, <从前向后>遍历, 找到一个比基准元素大的, 填入后面的空位置
 *      一直到前后遍历相遇结束......
 * 2）  通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。
 *      另一部分记录的 元素值比基准值大。
 * 3）  此时基准元素在其排好序后的正确位置
 * 4）  然后分别对这两部分记录用同样的方法继续进行排序，直到整个序列有序。
 *      T(n) = O(nlgn)
 * http://blog.csdn.net/hguisu/article/details/7776068
 * Created by chang on 17/8/12.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] list = {49, 38, 97, 76, 13, 65, 27};
        quickSort(list, 0, 6);
    }

    static void quickSort(int[] list, int startIdx, int endIdx) {

        //递归退出条件
        if (startIdx>=endIdx) {
            return;
        }

        //基准元素
        int pivotKey = list[startIdx];
        //需要被替换的位置
        int i =startIdx;
        //被替换的位置可能会替换成的值的位置
        int j = endIdx;
        //flag==0时,前面有空位,从后向前遍历数组
        //flag==1时,后面有空位,从前往后遍历数组
        int flag = 0;
        // 循环退出条件,
        // i一直加,j一直减,直到两者相等则退出
        while (i != j) {

            //while循环里面的i和j是随时变化的
            while (i!=j && 0==flag) {
                //如果遍历到的j位置的元素小于基准元素,则将j位置元素赋值给前面的空位i,
                //此时j位置变成空位,i位置向后移动一个位置,flag变成1
                //如果j元素大于基准元素, 则j位置向前移动一位,继续比较, 直到找到一个j位置小于基准元素
                if (list[j]<pivotKey) {
                    list[i++] = list[j];
                    flag = 1;
                } else {
                    j--;
                }
            }

            while (i!=j && 1==flag) {
                if (list[i]>pivotKey) {
                    list[j--] = list[i];
                    flag = 0;
                } else {
                    i++;
                }
            }
        }
        //将基准元素赋值给i位置, 则左边的元素都小于基准,右边都大于基准
        list[i] = pivotKey;
        //分别递归基准元素前面的元素,和后面的元素
        quickSort(list, startIdx, i-1);
        quickSort(list, i+1, endIdx);
        System.out.println();
    }
}
