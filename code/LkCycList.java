package com.suanfa.list;

import java.util.Stack;

/**
 * Created by chang on 17/6/30.
 * 单循环链表
 * http://www.cnblogs.com/smyhvae/p/4782595.html
 */
public class LkCycList {
    Node head;
    Node curr;
    int size;

    //构造空的单循环链表
    LkCycList () {
        this.head = new Node();
        this.head.next = head;
        this.curr = head;
        size = 0;
    }

    /**
     * 插入 : 索引大于size时插入到size位置
     * @param idx : 插入位置的索引(从0开始, 不算头结点)
     * @param data : 插入的数据
     */
    public void add(int idx, String data) {
        curr = head;
        //如果索引大于size,则插入到最后位置
        if (idx>size) {
            idx = size;
        }
        Node node = new Node(data, null);
        //循环链表找到要插入索引的前一个节点
        for (int i=0;i<idx;i++) {
            curr = curr.next;
        }
        //新节点的next赋值为当前节点的next
        node.next = curr.next;
        //当前节点的next赋值为新节点
        curr.next = node;
        //链表长度+1
        size++;
    }

     /**
     * 遍历循环链表 :
     * @param idx : 位置的索引(从0开始, 不算头结点)
     */
    public String get(int idx) {
        //下标从0开始, 去掉圈数
        idx = idx%size;
        curr = head;
        //定位到idx节点
        for (int i=0;i<=idx;i++) {
            curr = curr.next;
        }
        return curr.data;
    }

     /**
     * 遍历循环链表 :
     * @param idx : 位置的索引(从0开始, 不算头结点)
     */
    public String remove(int idx) {
        //下标从0开始, 去掉圈数
        idx = idx%size;
        curr = head;
        //定位到idx节点的前一个节点
        for (int i=0;i<idx;i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;
        return curr.data;
    }

    /**
     * 返回指定data的索引
     * @param data
     * @return int
     */
    public int getIdx(String data) {
        curr = head.next;
        int i = 0;
        for (;curr.data!=null;) {
            if (data.equals(curr.data)) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return -1;
    }

    /**
     * 判断链表是否有环,快慢指针
     * curr1每走一步, curr2走两步,
     * 如果有环则curr2迟早会追上curr1
     * @param head
     * @return int
     */
    public boolean hasCyc(Node head) {
        Node curr1 = head;
        Node curr2 = head;
        while (curr2!=null) {
            curr1 = curr1.next;
            curr2 = curr2.next.next;
            if (curr1==curr2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断链表是否有环
     * curr1每往后走一步, curr2从头节点头开始遍历
     * 如果有环则curr1和curr2相遇时,索引值不一样
     * @param head
     * @return int
     */
    public boolean hasCyc2(Node head) {
        Node curr1 = head;
        int i = 0;
        while (curr1!=null) {
            curr1 = curr1.next;
            i++;
            int j = 0;
            Node curr2 = head;
            for (;j<i;) {
                curr2 = curr2.next;
                j++;
                if (curr1==curr2&&i!=j) {
                    return true;
                }
            }
        }
        return false;
    }






    public static void main(String[] args) {
        LkCycList lkCycList = new LkCycList();
        lkCycList.add(0, "a");
        lkCycList.add(1, "b");
        lkCycList.add(2, "c");
        lkCycList.add(3, "d");
//        lkCycList.getComNodeByStack(lkCycList.head.next, lkCycList.head.next);

    }







    //链表的节点类
    class Node {
        //数据域
        String data;
        //指针域
        Node next;

        //构造头节点
        Node () {
            data = null;
            next = null;
        }
        //构造非头节点
        Node (String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
