package com.suanfa.list;

/**
 * Created by chang on 17/7/1.
 * 双向链表
 */
public class DbLkCycList {

    Node head;
    Node curr;
    int size;

    //初始化带头结点的双向循环链表
    DbLkCycList () {
        head = new Node();
        head.next = head;
        head.pre = head;
        this.curr = head;
        this.size = 0;
    }

    /**
     * 插入首元节点
     * @param data
     */
    void addFirst (String data) {
        //构造新节点
        Node node = new Node(data, null, null);
        //第一步:给新节点的pre和next赋值
        node.next = head.next;
        node.pre = head;
        //第二步:给旧节点重新赋值pre和next
        head.next.pre = node;
        head.next = node;
        size++;
    }

    /**
     * 链表末尾插入节点
     * @param data
     */
    void addLast (String data) {
        //构造新节点
        Node node = new Node(data, null, null);
        //第一步:给新节点的pre和next赋值
        node.next = head;
        node.pre = head.pre;
        //第二步:给旧节点重新赋值pre和next
        head.pre.next = node;
        head.pre = node;
        size++;
    }

    /**
     * 插入链表大于等于0的任意位置
     * @param idx:要插入的索引位置(从0开始)
     * @param data
     */
    void addMid (int idx, String data) {

        //遍历链表
        curr = head;
        //curr定位到要插入节点的前一个节点
        //如果已知链表size,则可以直接idx%size去掉圈数
        //然后通过idx与size/2比较确定是向前遍历还是向后遍历即可
        for (int i=0;i<idx;i++) {
            if (curr.next.data==null) {
                //如果当前节点的next是头节点,则跳过
                curr = curr.next.next;
            } else {  //i==0时,curr指向首元节点
                curr = curr.next;
            }
        }
        //构造新节点
        Node node = new Node(data, null, null);

        //则插入位置idx为curr的next
        //第一步:给新节点的pre和next赋值
        node.next = curr.next;
        node.pre = curr;
        //第二步:给旧节点重新赋值pre和next
        curr.next.pre = node;
        curr.next = node;
        size++;
    }

    /**
     * 删除idx位置的节点
     * @param idx:要删除node的索引位置(从0开始)
     */
    void remove (int idx) {

        //遍历链表
        curr = head;
        //已知链表size,则可以直接idx%size去掉圈数
        //然后通过idx与size/2比较确定是向前遍历还是向后遍历即可
        //可以用位操作优化%,暂时不会
        idx = idx%size;
        int halfSize = size>>1;
        //小于等于size/2时向后遍历
        if (idx<=halfSize) {
            for (int i=0;i<idx;i++) {
                //curr定位到idx的前一个节点
                curr = curr.next;
            }
            curr.next = curr.next.next;
            curr.next.pre = curr;
        } else { //向前遍历
            idx = idx%halfSize;
            for (int i=0;i<idx;i++) {
                curr = curr.pre;
            }
            curr.pre = curr.pre.pre;
            curr.pre.next = curr;
        }
        size--;
    }

    /**
     * 获取idx位置的节点的data
     * @param idx:要获取node的索引位置(从0开始)
     */
    String get (int idx) {

        //遍历链表
        curr = head;
        //已知链表size,则可以直接idx%size去掉圈数
        //然后通过idx与size/2比较确定是向前遍历还是向后遍历即可
        //可以用位操作优化%,暂时不会
        idx = idx%size;
        int halfSize = size>>1;
        //小于等于size/2时向后遍历
        if (idx<=halfSize) {
            for (int i=0;i<=idx;i++) {
                //curr定位到idx节点
                curr = curr.next;
            }
        } else { //向前遍历
            idx = idx%halfSize;
            for (int i=0;i<=idx;i++) {
                curr = curr.pre;
            }
        }
        return curr.data;
    }

    /**
     * 建立循环链表a-b-c...x-y-z-HEAD-a-b...
     * 输入参数idx,输出idx到idx的一圈链表节点的data
     * @param idx : 首元节点的位置为0, idx支持负数
     */
    void printData (int idx) {
        curr = head;
        for (int i='A';i<='Z';i++) {
            addLast((char)i+"");
        }
        curr = head;
        if (idx>=0) {
            for (int i=0;i<=idx;i++) {
                curr = curr.next;
            }
        } else {
            for (int i=idx;i<0;i++) {
                curr = curr.pre;
            }
        }
        for (int i=0;i<size;i++) {
            System.out.println(curr.data);
            if (curr.next.data==null) {
                curr = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
    }



    public static void main(String[] args) {
        DbLkCycList dbLkCycList = new DbLkCycList();
        dbLkCycList.printData(-6);
    }












    class Node {
        String data;
        Node pre;
        Node next;
        //头结点
        Node () {
            this.data = null;
            this.pre = null;
            this.next = null;
        }
        //非头结点
        Node (String data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

    }
}
