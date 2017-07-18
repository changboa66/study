package com.suanfa.string;

/**
 * 字符串匹配算法
 * BF暴力求解
 * Created by chang on 17/7/18.
 */
public class BruteForce {

    static int bf(String allString, String partString) {
        //全字符串
        char[] all = allString.toCharArray();
        //子串
        char[] part = partString.toCharArray();
        int len1 = all.length;
        int len2 = part.length;
        int i=0, j=0;
        while (i<len1 && j<len2) {
            if (all[i]==part[j]) {
                i++;
                j++;
                if (j==len2) {
                    return (i-len2);
                }
            } else {
                //全字符串移动到初始位置的下一位
                i = i - j + 1;
                //子串回溯到0位置
                j=0;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "a";
        System.out.println(bf(s1, s2));
    }
}
