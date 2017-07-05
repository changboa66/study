package com.suanfa.list;

/**
 * 队列的链式存储实现
 * 定义不保存数据的头节点方便操作
 * 头结点不是链表必须的
 * Created by chang on 17/7/5.
 */
public class LkQueue {

    //带头结点的队列(头结点不存数据,主要是方便操作)
    Node head;
    Node rear;

    //空队列
    LkQueue () {
        this.head = new Node();
        this.rear = this.head;
    }

    /**
     * 进队列
     * 时间复杂度O(1)
     */
    void enQueue(String data) {
        Node node = new Node(data);
        this.rear.next = node;
        rear = node;
    }

    /**
     * 出队列
     * 时间复杂度O(1)
     * @return data
     */
    String deQueue() {
        Node first = head.next;
        if (first==null) {
            System.out.println("队列为空!");
            return null;
        }
        if (first.next==null) {
            rear = head;
        }
        head.next = head.next.next;
        return first.data;
    }


    public static void main(String[] args) {
        LkQueue lkQueue = new LkQueue();
        lkQueue.enQueue("a");
        lkQueue.enQueue("b");
        lkQueue.enQueue("c");
        lkQueue.enQueue("d");
        System.out.println(lkQueue.deQueue());
        System.out.println(lkQueue.deQueue());
        System.out.println(lkQueue.deQueue());
        System.out.println(lkQueue.deQueue());
        System.out.println(lkQueue.deQueue());
    }



    class Node {
        String data;
        Node next;

        Node () {
            this.data = null;
            this.next = null;
        }
        Node (String data) {
            this.data = data;
            this.next = null;
        }
        Node (String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
