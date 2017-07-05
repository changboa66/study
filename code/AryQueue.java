package com.suanfa.list;

/**
 * 队列的顺序存储
 * 要点:
 * 1. 链式存储用不存数据的头结点可以方便的操作,
 *     顺序存储则不需要额外的头节点
 * 2. 每次出队列时改变front的值, 使队列头跟着改变
 * 3. 队列尾为需要插入的尾部位置
 * 4. 求模的方式使数组变成首尾相接的环状
 * 5. 队列的顺序存储形式必须在定义中给定元素个数, 不如链式存储方便
 * Created by chang on 17/7/5.
 */
public class AryQueue {
    String [] items;
    //队列头的索引
    int front;
    //队列尾部需要插入的那个空位置的索引(重点)
    int rear;
    //因为是顺序存储,所以在初始化节点必须给定数组的大小
    //队列数组最多能装多少元素
    int size;

    //初始化一个可以装size个元素的空队列
    //队列头和队列尾都指向索引0位置
    AryQueue(int size) {
        this.items = new String[size];
        this.front = 0;
        this.rear = 0;
        this.size = size;
    }

    /**
     进队列
     *时间复杂度为O(1)
     */
    void enAryQueue(String item) {
        // 如果队列头和需要插入的空位置重合,
        // 并且此位置已经有数据, 则说明队列满了
        if (front==rear && items[front]!=null) {
            System.out.println("队列满了!");
            return;
        }
        items[rear] = item;
        //通过求模的方式把数组构造成环状
        //如果size为2的倍数可以用位运算求模 x & (size-1)
        rear = (rear + 1) % size;
    }

    /**
     *出队列
     *时间复杂度为O(1)
     */
    String deAryQueue() {
        // 如果队列头和需要插入的空位置重合,
        // 并且此位置数据为空, 则说明队列为空
        if (front==rear && items[front]==null) {
            System.out.println("队列为空!");
            return null;
        }
        String item = items[front];
        items[front] = null;
        //通过求模的方式把数组构造成环状
        //如果size为2的倍数可以用位运算求模 x & (size-1)
        front = (front + 1) % size;
        return item;
    }

    public static void main(String[] args) {
        AryQueue aryQueue = new AryQueue(5);
        aryQueue.enAryQueue("a");
        aryQueue.enAryQueue("b");
        aryQueue.enAryQueue("c");
        aryQueue.enAryQueue("d");
        aryQueue.enAryQueue("e");
        aryQueue.enAryQueue("f");

        System.out.println(aryQueue.deAryQueue());
        System.out.println(aryQueue.deAryQueue());
        System.out.println(aryQueue.deAryQueue());
        System.out.println(aryQueue.deAryQueue());
        System.out.println(aryQueue.deAryQueue());
        System.out.println(aryQueue.deAryQueue());
    }




}
