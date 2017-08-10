package com.suanfa.sort;

/**
 * Created by chang on 17/8/9.
 */
public class MergeSort {

    public static void main(String[] args) {
//        int[] ints1 = {1, 3, 5, 7};
//        int[] ints2 = {4, 6, 8, 9};
//        int[] result = new int[8];
//        listSort(ints1, ints2, result);
        int[] ints = {5, 3, 8, 1, 2, 4, 9, 10};
        int[] result = new int[ints.length];
        mergeSort(ints, 0, ints.length-1, result);
        System.out.println();
    }

    static void mergeSort(int[] ints, int startIdx, int endIdx, int[] result) {
        if (startIdx >= endIdx)
            return;
        int midIdx = startIdx + ((endIdx - startIdx)>>1);
        mergeSort(ints, startIdx, midIdx, result);
        mergeSort(ints, midIdx+1, endIdx, result);
        //merge后数组的两部分都是已经排好序的
        //对数组ints的[startIdx~midIdx]和[midIdx+1~endIdx]两部分合并排序
        int low1 = startIdx;
        int high1 = midIdx;
        int low2 = midIdx + 1;
        int high2 = endIdx;
        //result数组需要赋值的下标为startIdx
        int k = startIdx;
        while (low1<=high1 && low2<=high2) {
            //low1<low2时,将low1索引处的值赋给result[k]
            //low1>low2时,将low2索引处的值赋给result[k]
            result[k++] = ints[low1]<ints[low2] ? ints[low1++] : ints[low2++];
        }

        // 第一个whlie必有一个为false
        // 若后半部分数组已经全部添加到了result上
        // 只剩下前半部分,则直接拼接到result的后面
        while (low1<=high1) {
            result[k++] = ints[low1++];
        }

        //同理,如果前半部分数组已经全部添加到了result上
        //则将后半部分直接拼接到result的后面
        while (low2<=high2) {
            result[k++] = ints[low2++];
        }
        System.arraycopy(result, startIdx, ints, startIdx, endIdx-startIdx+1);
        System.out.println();
    }


    /**
     * ~~~~~~~延伸~~~~~~~
     * 对两个已经排序好的数组合并成一个数组
     * @param ints1
     * @param ints2
     * @param result
     */
    static void listSort(int[] ints1, int[] ints2, int[] result) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i<ints1.length && j<ints2.length) {
            if (ints1[i]<ints2[j]) {
                result[k++] = ints1[i++];
            } else {
                result[k++] = ints2[j++];
            }
        }

        if (i==ints1.length) {
            while (j<ints2.length) {
                result[k++] = ints2[j++];
            }
        }
        if (j==ints2.length) {
            while (i<ints1.length) {
                result[k++] = ints1[i++];
            }
        }
        System.out.println();

    }
}
