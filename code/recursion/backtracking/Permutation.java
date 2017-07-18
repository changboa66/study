package com.suanfa.recursion;

import java.util.Arrays;

/**
 * 求一个数列的全排列
 * 假设要求字符串/数组的全排序，例如："123"的全排序，第1位有3种选择，
 * 第2位有2种选择，第3位1种选择，时间复杂度显然是3!.
 * 实际上容易知道，对于n个字符，全排序一共有n!种可能，是n!时间复杂度的．
 * 我们需要找一个算法求出所有的可能的排序，最好的办法是递归，
 * 如果选用循环，需要n层嵌套，不太现实．假设我们已经拥有一个
 * 函数 permutation(A[n])能将数组A[n]全排序，于是，
 * 我们可以将A[n]分为A[1]和A[n-1]两个子问题，其中，A[1]可以有n种选择，
 * 选择办法是：将A[1]和A[i]交换,其中，i=1,2,3,4......．
 * 记得交换后，需要复原．A[n-1]的全排序可以通过调用permutation()完成．
 * 例如:[1, 2, 3]的
 * 全排列为[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1],[3,1,2]
 * Created by chang on 17/7/18.
 */
public class Permutation {

    //数组两个数交换位置
    static void swap1(int[] list, int i, int j) {
        //当i不等于j时交换,i==j时不需要交换
        if (i==j)  return;
        list[i] = list[i] ^ list[j];
        list[j] = list[i] ^ list[j];
        list[i] = list[i] ^ list[j];
        //分析：
        // 前两个赋值语句：“a = a ^ b；”和“b = a ^ b；”
        // 相当于b = b ^ (a ^ b)，而b ^ a ^ b等于a ^ b ^ b。b ^ b的结果为0，
        // 因为同一个数与相向相^，结果必为0。因此b的值等于a ^ 0，即b = a;
        // 再执行第三个赋值语句：“a = a ^ b”。由于a的值等于(a ^ b)，
        // b的值等于(b ^ a ^ b)，因此，相当于a = a ^ b ^ b ^ a ^ b，
        // 即a的值等于a ^ a ^ b ^ b ^ b，等于a = b。
    }

    //数组两个数交换位置
    static void swap2(int[] list, int i, int j) {
        //当i不等于j时交换,i==j时不需要交换
        if (i==j)  return;
        list[i] ^= list[j];   // ^= 与 += 类似
        list[j] ^= list[i];
        list[i] ^= list[j];
    }

    //数组两个数交换位置
    static void swap3(int[] list, int i, int j) {
        //当i不等于j时交换,i==j时不需要交换
        if (i==j)  return;

        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    static void permutation(int[] list, int start, int length) {
        //递归退出条件start等于length
        //最后一次递归后start==length
        if (start==length) {
            for (int i : list)
                System.out.print(i+",");
            System.out.println();
            return;
        }

        for (int i=start;i<length;i++) {
            //start位置的值和数组其他位置的值交换
            swap1(list, start, i);
            //递归start位置
            permutation(list, start+1, length);
            //将第一次swap后的数据复原到为原状态
            swap1(list, start, i);
        }

    }

    public static void main(String[] args) {
        int[] list = {1, 2, 3};
        permutation(list, 0, 3);
    }
}
