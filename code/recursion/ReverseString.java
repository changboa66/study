package com.suanfa.recursion;

/**
 * 反转字符串
 * abcde --> edcba
 * Created by chang on 17/7/6.
 */
public class ReverseString {

    static String getReverseString(String s) {

        //string仅剩下一个字符时,直接返回
        if (s.length()==1) {
            return s;
        }
        //递归从1到(length()-1)所有字符
        //第0号字符都放在最后
        return getReverseString(s.substring(1)) + s.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(getReverseString("abcdefg"));
    }
}
