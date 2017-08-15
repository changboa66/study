package com.suanfa.sort;

/**
 * 桶排序
 * 1. 设置一个定量的数组当作空桶子。
 * 2. 寻访序列，并且把项目一个一个放到对应的桶子去。
 * 3. 对每个不是空的桶子里的数据进行排序(可在第二步中加入到桶的同时进行排序)
 * 4. 从不是空的桶子里把项目再放回原来的序列中。
 * Created by chang on 17/8/14.
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] ints = {14, 24, 39, 33, 48, 10};
        bucketSort(ints);
    }
    static void bucketSort(int[] list) {

        //10个桶
        Bucket[] buckets = new Bucket[10];

        for (int i : list) {

            //新建节点
            Bucket node = new Bucket(i);

            //将桶链表的的首元节点赋给bucket
            Bucket bucket = buckets[i/10];

            //引入头结点更容易操作链表
            //定义一个头结点
            Bucket head = new Bucket(-1, bucket);

            //如果首元节点为空, 则新节点为首元节点
            if (bucket==null) {
                buckets[i/10] = node;
                //进行下次遍历
                continue;
            }

            //首元节点不为空的情况
            //定义当前节点始终位于要插入位置的前一个位置
            //插入到桶里时顺便给桶里的元素排序
            Bucket curr = head;
            //定位到要插入节点的前一个节点即curr
            while (bucket!=null && i>bucket.data) {
                curr = bucket;
                bucket = bucket.next;
            }
            curr.next = new Bucket(i, bucket);
            //将头结点的next赋值给buckets[i/10]
            buckets[i/10] = head.next;
            System.out.println();
        }

        int idx = 0;
        //按顺序遍历桶
        //把元素重新赋值给list数组则排序结束
        for (Bucket bucket : buckets) {
            if (null==bucket) continue;
            //输出桶里的每个元素
            while (bucket!=null) {
                list[idx++] = bucket.data;
                bucket = bucket.next;
            }
        }

        System.out.println();

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
