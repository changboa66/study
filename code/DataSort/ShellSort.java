package com.suanfa.sort;

/**
 * 希尔排序
 * 是插入排序的一种更高效的改进版本。
 * 它的作法不是每次一个元素挨一个元素的比较。而是初期选用大跨步（增量较大）间隔比较，
 * 使记录跳跃式接近它的排序位置；然后增量缩小；最后增量为 1 ，这样记录移动次数大大减少，
 * 提高了排序效率。希尔排序对增量序列的选择没有严格规定。
 * -------------------------------------------------
 * 1. 先取一个正整数 d1(d1 < n)，把全部记录分成 d1 个组，所有距离为 d1 的倍数的记录看成一组，
 *    然后在各组内进行插入排序
 * 2. 然后取 d2(d2 < d1)
 * 3. 重复上述分组和排序操作；直到取 di = 1(i >= 1) 位置，即所有记录成为一个组，
 * 最后对这个组进行插入排序。一般选 d1 约为 n/2，d2 为 d1 /2， d3 为 d2/2 ，…， di = 1。
 * Created by chang on 17/8/10.
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] ints = {81, 99, 60, 75, 21, 33, 10, 9, 50};
        shellSort(ints);
    }

    static void shellSort(int[] ints) {

        //数组的长度
        int length = ints.length;
        //分组:第一次选length/2个组, 第二次选length/4个组....
        //group 从 length/2 到 1的过程
        //int[] ints = {81, 99, 60, 75, 21, 33, 10, 9, 50};
        //第一次分组[81, 21, 50],[99,33],[60,10],[75,9]
        //第一次每一组进行插入排序后[21,50,81],[33,99],[10,60],[9,75]
        //第二次分组为[21,50,81,33,99][10,60,9,75]
        //第二次每组插入排序后为[21,33,50,81,99][9,10,60,75]
        //第三次分组[21,33,50,81,99][9,10,60,75]
        //插入排序后为[9,10,21,33,50,60,75,81,99]
        //结束
        for (int group=(length>>1);group>0;group>>=1) {
            //遍历group
            for (int i=0;i<group;i++) {
                //对每一个group进行插入排序
                for (int j=i;j<length-group;) {
                    //group的已排好的最后一个元素的下标
                    //(第一个元素看作是已经排好序的序列)
                    int idx = j;
                    //group的未排好序的第一个元素的
                    //j+group为group的未排好序的第一个元素的下标
                    int start = ints[j+group];
                    //未排好序的元素<已经排好序的元素, 则将已排好序的数列的每个元素往后移,
                    while (idx>-1 && ints[idx]>start) {
                        ints[idx+group] = ints[idx];
                        idx = idx - group;
                    }
                    //将那个未排好序的元素插入到适当的位置
                    ints[idx+group] = start;
                    //排好一个元素,
                    //将未排好序的的下标后移group
                    j = j + group;
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
