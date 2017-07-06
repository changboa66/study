package com.suanfa.list;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 两个队列实现栈的功能
 * 两个队列始终有一个为空
 * 入栈:如果两个队列都为空,则随便找一个入队列;如果有非空队列, 则入非空队列
 * 出队的时候把queue1的(size-1)个元素移动到queue2,queue1剩下一个元素,此时出队列就是出栈
 * Created by chang on 17/7/6.
 */
public class Queue2Stack {

    LinkedBlockingQueue<String> linkedBlockingDeque1 = new LinkedBlockingQueue<>();
    LinkedBlockingQueue<String> linkedBlockingDeque2 = new LinkedBlockingQueue<>();

    /**
     * 入栈操作
     * @param item
     */
    void push(String item) {
        //如果queue2不为空, 则入queue2
        if (!linkedBlockingDeque2.isEmpty()) {
            linkedBlockingDeque2.add(item);
        } else { //包含<queue1不为空>和<queue1,queue2都为空>两个条件
            linkedBlockingDeque1.add(item);
        }

    }

    /**
     * 出栈
     * 两个queue必须有一个始终为空
     */
    String pop() throws Exception{

        //两个队列都为空
        if (linkedBlockingDeque1.isEmpty()
                && linkedBlockingDeque2.isEmpty()) {
            System.out.println("为空!");
            return null;
        }

        //queue1为空,则把queue2的元素插入queue1中,queue2只剩1个元素
        if (linkedBlockingDeque1.isEmpty()) {
            while (linkedBlockingDeque2.size() > 1) {
                linkedBlockingDeque1.add(linkedBlockingDeque2.take());
            }
            return linkedBlockingDeque2.take();
        } else {  //queue2为空的情况
            while (linkedBlockingDeque1.size() > 1) {
                linkedBlockingDeque2.add(linkedBlockingDeque1.take());
            }
            return linkedBlockingDeque1.take();
        }
    }


    public static void main(String[] args) throws Exception{
        Queue2Stack queue2Stack = new Queue2Stack();
        queue2Stack.push("a");
        queue2Stack.push("b");
        queue2Stack.push("c");

        System.out.println(queue2Stack.pop());
        System.out.println(queue2Stack.pop());
        System.out.println(queue2Stack.pop());
        System.out.println(queue2Stack.pop());
        System.out.println(queue2Stack.pop());
        System.out.println(queue2Stack.pop());
    }
}
