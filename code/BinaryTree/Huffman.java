package com.suanfa.binaryTree;

import com.google.common.collect.*;

import java.util.*;

/**
 * 哈夫曼编码
 * 1. 创建一个优先级队列
 * 2. 创建哈夫曼树
 * 3. 创建哈夫曼编码表
 * 4. 对字符串按哈夫曼表进行编码
 * 5. 解码
 * Created by hexun.com on 2017/7/28.
 */
public class Huffman {

    public static void main(String[] args) throws Exception{
        Character[] chars = {'a', 'b', 'c', 'd', 'a', 'e'};
        PriorityQueue<Node> priorityQueue = getQueue(chars);
        BiMap<String, String> map = HashBiMap.create();
        Node root = huffmanTree(priorityQueue);
        huffmanTable(root, map, "");
        String s = "abccabeeedae";
        String sCode = encode(s, map);
        System.out.println(sCode);
        StringBuffer sb = new StringBuffer();
        decode(sCode, root, root, sb);
        System.out.println(sb.toString());
    }

    /**
     * 解码
     * @param sCode : 哈夫曼编码
     * @param root0 : 哈夫曼树的根节点
     * @param node : 递归过程的节点
     * @param sb : 需要返回的字符串
     *
     */
    static void decode(String sCode, Node root0, Node node, StringBuffer sb) {
        if (null==node.left && node.right==null) {
            sb.append(node.data);
            //找到一个字母后,使根节点回溯到根节点,重新开始递归
            decode(sCode, root0, root0, sb);
            return;
        }
        //code不为空的时候一直向下递归
        if (sCode.length()>0) {
            //判断单个字符是"0"还是"1"
            String single = sCode.charAt(0) + "";
            if (single.equals("0")) {
                decode(sCode.substring(1), root0, node.left, sb);
            } else {
                decode(sCode.substring(1), root0, node.right, sb);
            }
        }
    }
    /**
     * 哈夫曼编码
     * @param s : 需要进行编码的字符串
     * @param map : 哈夫曼码表
     * return String 字符串的哈夫曼编码
     */
    static String encode(String s, BiMap<String, String> map) {
        StringBuilder sbCode = new StringBuilder();
        for (int i=0;i<s.length();i++) {
            String c = s.charAt(i) + "";
            String value = map.get(c);
            sbCode.append(value);
        }
        return sbCode.toString();
    }

    /**
     * 创建哈夫曼表
     * @param root : 哈夫曼树的根节点
     * @param map : 需要生成的哈夫曼表
     * @param value : 每一个字符的哈夫曼编码
     */
    static void huffmanTable(Node root, BiMap<String, String> map, String value) {

        if (root.left==null && root.right==null) {
            String key = root.data;
            map.put(key, value);
            System.out.print("key="+key);
            System.out.println(", value="+value);
        }
        if (root.left!=null) {
            value += "0";
            huffmanTable(root.left, map, value);
        }
        if (root.right!=null) {
            //经过上面的if1后,ss会多出最后一位0
            value = value.substring(0, value.length()-1);
            value += "1";
            huffmanTable(root.right, map, value);
        }
    }

    /**
     * 创建一个哈夫曼树
     */
    static Node huffmanTree (PriorityQueue<Node> priorityQueue) {
        //直到剩下一个节点即为哈夫曼树的root节点
        for (;priorityQueue.size()>1;) {
            //第一个出队列的做为左孩子
            Node left = priorityQueue.poll();
            //第二个出队列的做为右孩子
            Node right = priorityQueue.poll();
            //两个节点的父节点的值,频次
            String pData = left.data + right.data;
            int pFrequency = left.frequency + right.frequency;
            Node root = new Node(pData, pFrequency, left, right);
            left.parent = root;
            right.parent = root;
            priorityQueue.add(root);
        }
        return priorityQueue.poll();
    }


    /**
     * 将数组存入优先级队列中
     * @param chars:必须是Character[]类型,
     * 如果传入char[]类,HashMultiset.create(..)会出错
     * @return PriorityQueue
     * 统计字符在字符串中出现的次数也可以这样写:
     * int count = 0, index = 0;
     * while (true) {
     *   index = string.indexOf(target, index + 1);
     *   if (index > 0) {
     *     count++;
     *   } else {
     *     break;
     *   }
     * }
     */
    static PriorityQueue<Node> getQueue(Character[] chars) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        //元素可以重复的set, 可以直接取到元素的个数
        HashMultiset multiset = HashMultiset.create(Arrays.asList(chars));
        //multiset.elementSet():元素的集合(没有重复)
        Iterator<Character> iterator = multiset.elementSet().iterator();
        for (;iterator.hasNext();) {
            Character character = iterator.next();
            Node node = new Node(character+"", multiset.count(character));
            priorityQueue.add(node);
        }
        return priorityQueue;
    }

    static class Node implements Comparable<Node>{

        //字符
        String data;
        //出现的频次
        int frequency;
        //左孩子
        Node left;
        //右孩子
        Node right;
        //父节点
        Node parent;

        Node(String data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }
        Node(String data, int frequency, Node left, Node right) {
            this(data, frequency);
            this.left = left;
            this.right = right;
        }
        Node(String data, int frequency, Node left, Node right, Node parent) {
            this(data, frequency, left, right);
            this.parent = parent;
        }

        //按frequency的大小按自然排序
        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }

    }


}
