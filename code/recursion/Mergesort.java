package com.suanfa.recursion;

/**
 * 归并排序
 * 先递归,后合并
 * 归并排序中“分”与“合”的过程是结合在一起的，
 * 即每一趟都在做“分”与“合”的工作，并不是先“分”完再“合”;
 *
 * （1）分治法的三个步骤
 * 设归并排序的当前区间是R[low…high]，分治法的三个步骤是：
 * ①分解：将当前区间一分为二，即求分裂点
 * ②求解：递归地对两个子区间R[low…mid]和R[mid+1…high]进行归并排序；
 * ③组合：将已排序的两个子区间R[low…mid]和R[mid+1…high]归并为一个有序的区间R[low…high]。
 * 递归的终结条件：子区间长度为1（一个记录自然有序）。
 * Created by chang on 17/7/10.
 */
public class Mergesort {

    //第一步:先递归到只剩下两个元素
    static void recursion(int[] from, int low, int high, int[] to) {
        if (low>=high) {
            return;
        }
        int middle = low + ((high-low)>>1);

        // 当递归到low==middle==0时,进入下一次循环会return,
        // 所以此时的low==middle==0; 同理middle+1==high==1;
        // 这时可以看做from[0]和from[1]是已经排好序的, 合并from[0]和from[1]
        recursion(from, low, middle, to);
        recursion(from, middle+1, high, to);
        mergeSort(from, low, middle, high, to);
    }

    //第二步:合并排序
    //合并前from[low到middle], from[middle+1到high]是已经排好序的
    //合并后from数组和to数组都是已经排好序的
    static void mergeSort(int[] from, int low, int middle, int high, int[] to) {
        //将from分解成两个数组[low----middle]和[middle+1----high]
        //分别重新定义low和high
        int low1 = low;
        int high1 = middle;
        int low2 = middle + 1;
        int high2 = high;

        //to数组的最低位
        int idx = low;

        //合并两个已经排好序的数组到to里面
        //两个数组只要有一个的低位超过高位则退出
        //把另一个数组的元素直接加到已经排序好的数组后面(下一个while)
		//以下可简化为用三木运算符表示:
		// while (low1<=high1 && low2<=high2)
		// 		from[low1] < from[low2] ? to[idx++] = from[low1++] : to[idx++] = from[low2++];
        while (low1<=high1 && low2<=high2) {
            if (from[low1]<from[low2]) {
                to[idx++] = from[low1++];
            } else {
                to[idx++] = from[low2++];
            }
        }

        while (low1<=high1) {
            to[idx++] = from[low1++];
        }

        while (low2<=high2) {
            to[idx++] = from[low2++];
        }

        //将排序好的to数组赋值给未排序好的数组,上一层的排序需要用到
        //from=[3, 2, 4, 1] --> to=[2, 3, 0, 0] --> from=[2, 3, 4, 1]
	for (idx = low; idx <= high; idx++) {
            from[idx] = to[idx];
        }

    }


    public static void main(String[] args) {
        int[] ints = {4, 3, 2, 1};
        int[] to = new int[4];
        recursion(ints, 0, 3, to);
        System.out.println(ints);
    }
}
