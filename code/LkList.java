package com.suanfa.list;

import java.util.Stack;

/**
 * Created by chang on 17/6/29.
 * 单链表方法集合
 */
public class LkList {

    //头结点(非第一个节点)
    Node head;

    //当前节点
    Node curr;

    //Lklist的节点个数(不包括头节点)
    int size;

    //初始化链表为空链表
    LkList () {
        this.head = new Node();
        this.curr = head;
        size = 0;
    }

    /**
     * 头插法:向链表头部加入元素
     * 时间复杂度为O(1)
     */
    public void addFirst(String data) {
        //要加入的数据构造成节点
        Node node = new Node(data, null);
        node.next = head.next;
        head.next = node;
        size++;
    }
    /**
     * 尾插法:向链表尾部加入元素
     * 时间复杂度为O(n)
     */
    public void addLast(String data) {
        //要加入的数据构造成节点
        Node node = new Node(data, null);
        curr = head;
        for (int i=0;i<size;i++) {
            curr = curr.next;
        }
        curr.next = node;
        size++;
    }
    /**
     * 向链表中间加入元素
     * 时间复杂度为O(n)
     */
    public void addMid(int idx, String data) {
        //要加入的数据构造成节点
        Node node = new Node(data, null);
        curr = head;
        //curr定位到要插入位置的前一个位置
        for (int i=0;i<idx;i++) {
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
        size++;
    }

    /**
     * 返回索引位置节点的data
     * @param idx (索引从0开始)
     * @return data
     */
    public String get(int idx) {
        curr = head;
        for (int i=0;i<=idx;i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * 返回data所在的索引
     * @param data (索引从0开始)
     * @return data
     */
    public int getIdx(String data) {
        curr = head;
        for (int i=0;i<size;i++) {
            curr = curr.next;
            if (data.equals(curr.data)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定索引位置的node
     * 时间复杂度为O(n)
     */
    public Node remove(int idx) {
        curr = head;
        //定位到要删除节点的头一个节点
        for (int i=0;i<idx;i++) {
            curr = curr.next;
        }
        Node node = curr.next;
        curr.next = curr.next.next;
        size--;
        return node;
    }
    /**
     * 删除delNode
     * 时间复杂度为O(n)或O(1)
     */
    public void remove(Node delNode) {
        curr = head;
        //如果要删除的节点是尾节点O(n)
        if (delNode.next==null) {
            for (int i=0;i<(size-1);i++) {
                curr = curr.next;
            }
            curr.next = null;
        } else {
            //非尾节点则将要删除节点的下一个节点的data赋值给要删除的节点
            //则问题转化为删除delNode的下一个节点O(1)
            delNode.data = delNode.next.data;
            delNode.next = delNode.next.next;
        }
        size--;
    }
    /*******************************************************************************/
    /**
     * 求单链表的节点个数
     */
    public int getSize() {
        int i = 0;
        curr = head.next;
        for (;curr!=null;) {
            curr = curr.next;
            i++;
        }
        return i;
    }

    /**
     * 求单链表的倒数第K个节点
     */
    public String getLidx(int lidx) {
        Node curr = head.next;
        Node curr1 = head.next;
        //临时节点向后移动(lidx-1)个位置
        for (int i=0;i<(lidx-1);i++) {
            curr1 = curr1.next;
        }
        //curr1.next==null时,curr1在最后一个节点位置
        for (;curr1.next!=null;) {
            curr = curr.next;
            curr1 = curr1.next;
        }
        return curr.data;
    }

    /**
     * 求单链表的中间节点
     * 初始都定位到首元节点,curr走一步,curr1走两步
     * 当curr1走到最后位置时,curr正好位于中间位置
     */
    public String getMid() {
        Node curr = head.next;
        Node curr1 = head.next;
        //curr1最后位于最后位置,或倒数第2位置
        for (;curr1.next!=null&&curr1.next.next!=null;) {
            curr = curr.next;
            curr1 = curr1.next.next;
        }
        return curr.data;
    }

    /**
     * 合并两个已经排好序的单链表
     * list1 = 1-3-5-7
     * list2 = 2-4-6-8-9-10
     */
    public Node mergeLkList(Node head1, Node head2) {
        //定义合并以后的链表的头节点
        Node head = new Node();
        Node nCurr = head;
        //当前节点定位到链表的第一个节点
        Node curr1 = head1.next;
        Node curr2 = head2.next;
        //当一个链表到达尾部时,退出
        while (curr1!=null && curr2!=null) {
            if (curr1.data.compareTo(curr2.data)<0) {
                //临时节点保存当前位置下一个节点
                Node temp = curr1.next;
                //新链表的当前节点的next指向旧链表的当前节点
                nCurr.next = curr1;
                //新链表后移
                nCurr = nCurr.next;
                //旧链表后移
                curr1 = temp;
            } else {
                Node temp = curr2.next;
                nCurr.next = curr2;
                nCurr = nCurr.next;
                curr2 = temp;
            }
            //while退出前,新链表的当前位置肯定在短链表的最后一个元素位置处
            //此时只要把当前位置的next指向长链表剩余的元素即可
            if (curr1==null) {
                nCurr.next = curr2;
            } else {
                nCurr.next = curr1;
            }
        }
        return head;
    }


    /**
     * 单链表的翻转:头插法
     *  1-2-3-4
     *  转为 4-3-2-1
     * @param head
     */
    public Node revList(Node head) {
        //新的空链表
        Node nHead = new Node();
        curr = head.next;
        for (;curr!=null;) {
            //临时节点保存旧表当前节点下一个节点
            Node temp = curr.next;
            //当前节点的next节点指向新的头节点的next节点
            curr.next = nHead.next;
            //头节点的next节点为当前节点
            nHead.next = curr;
            //旧表当前节点后移
            curr = temp;
        }
        return nHead;
    }

    /**
     * 单链表的翻转:尾插法:运用栈
     *  1-2-3-4
     *  转为 4-3-2-1
     * @param head
     */
    public Node revListByStack(Node head) {
        //新的空链表
        Node nHead = new Node();
        Node nCurr = nHead;
        curr = head.next;
        Stack<Node> stack = new Stack<>();
        for (;curr!=null;) {
            //临时变量保存当前节点的next
            Node temp = curr.next;
            //当前的节点的next赋值为null,
            //否则将该节点push到栈里时会放进去一个链表而不是节点
            curr.next = null;
            stack.push(curr);
            //当前节点后移
            curr = temp;
        }
        for (;!stack.isEmpty();) {
            nCurr.next = stack.pop();
            nCurr = nCurr.next;
        }
        return nHead;
    }

    /**
     * 从尾到头输出单链表节点的data
     *  1: 栈
     *  2: 递归
     * @param first : 链表的头节点(非头节点)
     */
    public void recFromList(Node first) {
        // first的next不为空时,递归链表,
        // 直到first.next==null,此时first为链表最后一个节点
        if (first.next!=null) {
            recFromList(first.next);
        }
        //从尾到头打印链表的每一个节点的data
        System.out.println(first.data);
    }





    public static void main(String[] args) {
        LkList lkList = new LkList();
        lkList.addLast("1");
        lkList.addLast("3");
        lkList.addLast("5");
        lkList.addLast("7");
        lkList.recFromList(lkList.head.next);
        System.out.println("");
    }




    //链表的节点类
    class Node {
        //数据域
        String data;
        //指针域
        Node next;

        //构造头节点的构造方法
        Node () {
            data = null;
            next = null;
        }
        //构造非头节点用到的构造方法
        Node (String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}

