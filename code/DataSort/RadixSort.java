package com.suanfa.sort;

/**
 * 基数排序
 * 基数排序是非比较排序算法,算法的时间复杂度是O(n).
 * 相比于快速排序的O(nlgn),从表面上看具有不小的优势.
 * 但事实上可能有些出入,因为基数排序的n可能具有比较大的系数K.
 * 因此在具体的应用中,应首先对这个排序函数的效率进行评估;
 * 基数排序的主要思路是,将所有待比较数值(注意,必须是正整数)统一为同样的数位长度,
 * 数位较短的数前面补零. 然后, 从最低位开始, 依次进行一次稳定排序(我们常用上一篇blog介绍的计数排序算法,
 * 因为每个位可能的取值范围是固定的从0到9).这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列.
 * Created by chang on 17/8/13.
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] ints = {140, 222, 39, 33, 1, 48, 1000};
        radixSort(ints);
    }

    static void radixSort(int[] list) {
        //遍历一遍找到数组的最大数
        for (int i=1;i<list.length;i++) {
            if (list[i-1]>list[i]) {
                swap(list, i-1, i);
            }
        }

        //将最大值转换成String并求出最大值的长度
        String max = list[list.length-1] + "";
        int len = max.length();
        int mLen = max.length();

        //定义string数组(保存int数组转成string后的数组)
        String[] strings = new String[list.length];
        //将int数组转成string数组, 并将每个元素
        //统一为同样的数位长度,数位较短的数前面补零
        for (int i=0;i<list.length;i++) {
            String temp = list[i] + "";
            while (temp.length()<len) {
                temp = "0" + temp;
            }
            strings[i] = temp;
        }

        //将string数组的每个元素从后向前遍历排列插入到bucket中
        while (len-->0) {
            Bucket[] buckets = new Bucket[10];
            for (String string : strings) {
                //while里已经--, 所以此时len为最后一个元素的下标
                char c = string.charAt(len);
                int idx = Integer.parseInt(c+"");
                // 需要用尾插法
                // 定义头结点方便操作链表
                Bucket head = new Bucket(-1);
                Bucket curr = buckets[idx];
                if (curr==null) {
                    buckets[idx] = new Bucket(Integer.parseInt(string));
                    continue;
                }
                //头结点的next为首元节点
                head.next = curr;
                while (curr.next!=null) {
                    curr = curr.next;
                }
                //插入到curr的next
                curr.next = new Bucket(Integer.parseInt(string));
                buckets[idx] = head.next;
            }
            int x = 0;
            for (Bucket b : buckets) {
                //遍历非空的桶里面的元素
                while (b!=null) {
                    // 同上, 将元素都变成等长
                    // 然后赋给strings数组
                    String s = b.data+"";
                    while (s.length()<mLen){
                        s = "0" + s;
                    }
                    strings[x++] = s;
                    b = b.next;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static void swap(int[] ints, int i, int j) {
        if (i==j) return;
        //位运算交换ints[i]和ints[j]
        ints[i] = ints[i] ^ ints[j];
        ints[j] = ints[i] ^ ints[j];
        ints[i] = ints[i] ^ ints[j];
    }

    private static class Bucket {
        int data;
        Bucket next;

        Bucket (int data) {
            this.data = data;
        }
        Bucket (int data, Bucket next) {
            this(data);
            this.next = next;
        }
    }
}