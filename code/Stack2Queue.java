package com.suanfa.list;

import java.util.Stack;

/**
 * 两个栈实现队列的功能
 * Created by chang on 17/7/6.
 */
public class Stack2Queue {

    // stack1:接收入队列的栈;
    // stack2:出队列的栈;
    Stack<String> stack1 = new Stack<>();
    Stack<String> stack2 = new Stack<>();

    /**
     * 入队列操作
     * @param item
     */
    void enQueue (String item) {
        stack1.push(item);
    }

    /**
     * 出队列操作
     * return item
     */
    String deQueue() {

        //stack1和stack2都为空
        if (stack2.isEmpty() && stack1.isEmpty()) {
            System.out.println("请先入队列!");
            return null;
        }

        //stack1不为空,stack2为空, 则把stack1全部倒入stack2
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }


    public static void main(String[] args) {
        Stack2Queue stack2Queue = new Stack2Queue();
        stack2Queue.enQueue("a");
        stack2Queue.enQueue("b");
        stack2Queue.enQueue("c");
        stack2Queue.enQueue("d");

        System.out.println(stack2Queue.deQueue());
        System.out.println(stack2Queue.deQueue());
        System.out.println(stack2Queue.deQueue());
        System.out.println(stack2Queue.deQueue());
        System.out.println(stack2Queue.deQueue());
        System.out.println(stack2Queue.deQueue());
    }


}
