package com.suanfa.string;

/**
 * KMP字符串匹配算法
 * 最主要的也是最难点是求next数组
 * 对于P的前j+1个序列字符:
 * 若p[k] == p[j]，则next[j+1] = next [j] + 1 = k + 1；
 * 若p[k ] ≠ p[j]，如果此时p[ next[k] ] == p[j]，
 * 则next[ j + 1 ] =  next[k] + 1，否则继续递归前缀索引k = next[k]，
 * 而后重复此过程。 相当于在字符p[j+1]之前不存在
 * 长度为k+1的前缀"p0 p1, …, pk-1 pk"跟后缀“pj-k pj-k+1, …, pj-1 pj"相等，
 * 那么是否可能存在另一个值t+1 < k+1，使得长度更小的前缀 “p0 p1, …, pt-1 pt” 等于
 * 长度更小的后缀 “pj-t pj-t+1, …, pj-1 pj” 呢？如果存在，那么这个t+1 便是next[ j+1]的值，
 * 此相当于利用已经求得的next 数组（next [0, ..., k, ..., j]）进行P串前缀跟P串后缀的匹配。
 * Created by chang on 17/7/20.
 */
public class KMPSubSearch {


    static int kmp(String s, String p) {
        //字符串的长度
        int len1 = s.length();
        int len2 = p.length();
        //字符串的位置指针
        int i = 0, j = 0;
        int[] next = next(p);
        while (i<len1 && j<len2) {
            //如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），
            //都令i++，j++, 则继续往下比较
            if (j == -1 || s.charAt(i)==p.charAt(j)) {
                i++;
                j++;
            } else {  //否则i不变,j根据next数组回溯
                j = next[j];
            }
        }

        //跳出循环后若j的长度增加到了len2则说明有匹配
        if (j==len2) {
            return (i-j);
        }
        return -1;
    }
	
    //next数组表示子串p下一次移动到的j的位置
    static int[] next (String p) {

        //字符串的长度
        int len = p.length();

        //初始化next数组
        int[] next = new int[len];

        //定义next数组的第一个元素的值为-1
        next[0] = -1;

        //定义前缀位置k,后缀位置j
        int k = -1, j = 0;

        //已知p0,p1,pk-1 == pj-k,pj-k+1,pj-1得到next[j] = k
        //现求next[j+1]
        while (j<len-1) {
            //-1==k:位置为1的next值必然为0
            //由next[j]=k和p.charAt(k)==p.charAt(j)得到next[j+1]=k+1
            if (-1==k || p.charAt(k)==p.charAt(j)) {
                ++k;
                ++j;
                if (p[j] == p[k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
           	} else {
		 next[j] = k;
            } else {
                // 最大长度不匹配了所以
                // 找一个次长串去匹配
                k = next[k];
            }
        }
        return next;
    }


}
