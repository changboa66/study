package com.suanfa.string;

/**
 * 利用分治递归思想解决大整数相乘问题
 * 例如:12345678*87654321
 * 可以分为 a==8765; b==4321; c==1234; d==5678
 * 结果就等于[a*c(a*d+b*c)b*d];a*c为高位,a*d+b*c为中位,b*d为低位,
 * 有进位的向上进a*c,a*d,b*c,b*d又可递归
 * 12345678909876543210X98765432101234567890
 * =1219326312117055326552354825111263526900
 * 9999999999999999999988888888666655544*88888888877777666123456789
 * =888888888777776661233580235659381728610974085310844088831288216
 * Created by chang on 17/7/21.
 */


public class BigDataMultiplication {

    public static void main(String[] args) {
        String s1 = "9999999999999999999988888888666655544";
        String s2 = "88888888877777666123456789";
        System.out.println(multi(s1, s2));
    }


    static String multi(String s1, String s2) {
        //被乘数和乘数字符串的长度
        int len1 = s1.length();
        int len2 = s2.length();
        int maxLen = Math.max(len1, len2);
        //填充0到较短的字符串前面,使两个字符串达到一样长
        if (len1>len2) {
            s2 = fill(s2, maxLen);
        } else {
            s1 = fill(s1, maxLen);
        }

        //将每个字符串分成高位和低位两部分
        String a;
        String b;
        String c;
        String d;

        //maxLen是偶数时字符串平分
        //maxLen是奇数时a,c比b,d字符串长度小1
        a = s1.substring(0, maxLen/2);
        b = s1.substring(maxLen/2);
        c = s2.substring(0, maxLen/2);
        d = s2.substring(maxLen/2);

        String ac;
        String ad;
        String bc;
        String bd;

        //a, b, c, d都小于等于9999时,可以直接求解
        if (maxLen<=8) {
            long t = Long.parseLong(s1) * Long.parseLong(s2);
            return ""+t;
        }

        ac = multi(a, c);
        ad = multi(a, d);
        bc = multi(b, c);
        bd = multi(b, d);

        //中间位
        String adbc = plus(ad, bc);

        // 若低位的长度大于整个字符串一半的长度,
        // 则需要进位
        if(bd.length()>maxLen/2) {

            //先把低位的字符串拿出来(先拿高位会出错,比如maxLen=9,bd=123456的情况)
            //低位剩下从maxLen/2(包括)到结尾的长度的字符串
            //先把低位保存在临时变量里
            String bdTemp;
            //如果总长是偶数则直接取
            //总长是奇数需要向高位移动一位
            if ((maxLen&1)==0) {
                bdTemp = bd.substring(bd.length()-maxLen/2);
            } else {
                bdTemp = bd.substring(bd.length()-maxLen/2-1);
            }
            //从后往前遍历求临时变量在字符串bd中的索引
            int bdTempLastIdx = bd.lastIndexOf(bdTemp);
            //需要进位的数
            String temp = bd.substring(0, bdTempLastIdx);
            adbc = plus(adbc, temp);
            //将bdTemp赋值给bd
            bd = bdTemp;
        }

        //判断中间位是否需要进位
        if(adbc.length()>maxLen/2) {
            String adbcTemp;
            if ((maxLen&1)==0) {
                adbcTemp = adbc.substring(adbc.length()-maxLen/2);
            } else {
                adbcTemp = adbc.substring(adbc.length()-maxLen/2-1);
            }
            int adbcTempLastIdx = adbc.lastIndexOf(adbcTemp);
            String temp = adbc.substring(0, adbcTempLastIdx);
            ac = plus(ac, temp);
            adbc = adbcTemp;
        }
        String s = ac + adbc + bd;
        //去掉字符串最前面的所有0
        s = s.replaceFirst("^0*", "");

        return s;
    }

    /**
     * 求中间a*d + b*c的方法
     * @param ad
     * @param bc
     * @return 相加后值的字符串s
     */
    static String plus(String ad, String bc) {
        //被乘数和乘数字符串的长度
        int len1 = ad.length();
        int len2 = bc.length();
        int maxLen = Math.max(len1, len2);
        //填充0到较短的字符串前面,使两个字符串达到一样长
        if (len1>len2) {
            bc = fill(bc, maxLen);
        } else {
            ad = fill(ad, maxLen);
        }
        //进位
        int carry = 0;
        //相加后返回的字符串
        String string = "";
        for (int i=maxLen-1; i>-1; i--) {
            Long temp = Long.parseLong(bc.charAt(i)+"")
                    + Long.parseLong(ad.charAt(i)+"") + carry;
            if (temp>9) {
                //只保留各位
                temp = temp - 10;
                //进一位
                carry = 1;
            } else {
                //不存在进位的情况下carry=0
                carry = 0;
            }
            string = temp + string;
        }
        if (carry==1) {
            string = "1" + string;
        }
        return string;
    }

    /**
     * 辅助方法, 使两个字符串的长度达到一致
     * @param s
     * @param maxLen
     * @return s
     */
    static String fill(String s, int  maxLen) {
        //较短的字符串前面填充0
        while (s.length()<maxLen) {
            s = "0" + s;
        }
        return s;
    }

}
