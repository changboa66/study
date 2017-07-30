package com.suanfa.search;

/**
 * 顺序查找的简单实现
 * Created by chang on 17/7/30.
 */
public class SequenceSearch {

    public static void main(String[] args) {
        System.out.println(sqSearch3("abcdeddadfete", 'f'));
    }

    /**
     * 从头遍历到结尾
     * @param s
     * @param c
     * @return
     */
    static int sqSearch(String s, char c) {
        int len = s.length();
        for (int i=0;i<len;i++) {
            if (s.charAt(i)==c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 遍历字符串长度的一半
     * @param s
     * @param c
     * @return
     */
    static int sqSearch2(String s, char c) {
        int len = s.length();
        int halfLen = len>>1;
        for (int i=0;i<halfLen;i++) {
            if (s.charAt(i)==c) {
                return i;
            } else if (s.charAt(i+halfLen)==c) {
                return (i+halfLen);
                //如果len是奇数,判断最后一位
            } else if (((halfLen&1)==1) && s.charAt(len-1)==c) {
                return(len-1);
            }
        }
        return -1;
    }
    /**
     * 设置哨兵减少判断次数
     * @param s
     * @param c
     * @return
     */
    static int sqSearch3(String s, char c) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int i = len-1;
        // 判断第一个字符和要比较的字符是否相等
        // 如果相等则返回,
        // 不相等则将字符串第一个字符设置成哨兵
        if (s.charAt(0)==c) {
            return 0;
        } else {
            chars[0] = c;
        }
        for (;chars[i]!=c;) {
            i--;
        }
        //前面已经判断是第一个元素不相同,
        //所以如果是第一个元素相同,证明是手动设置相等的 所以返回-1
        //其他情况返回正确的index
        return i==0?-1:i;
    }

}
