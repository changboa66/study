package com.suanfa.list;

import com.sun.deploy.util.StringUtils;

import java.util.Stack;

/**
 * Created by chang on 17/7/2.
 */
public class ArrayStack {
    String[] element;
    //当前元素个数
    int size;
    //栈顶(栈最高处元素往上一个位置)
    int top;
    //栈底(插入的第一个元素的位置)
    int base;

    //建立空栈
    ArrayStack(int count) {
        size = 0;
        element = new String[count];
        top = 0;
        base = 0;
    }

    //入栈
    void push(String item) {
        element[top++] = item;
    }

    //出栈
    String pop() {
        if (top>0)
            return element[--top];
        else
            return null;
    }

    //判断栈是否为空
    boolean isEmpty() {
        return (top-base)==0;
    }

    /**
     * 二进制转十进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param bin
     * @return
     */
    int bin2dec(String bin) {
        for (int i=0;i<bin.length();i++) {
            element[i] = bin.charAt(i)+"";
            top++;
        }
        int dec = 0;
        //pop()后top会变化
        int temp = top;
        for (int i=0;i<temp;i++) {
            //栈顶第1位*2^0+第2位*2^1+第3位*2^2........
            dec = dec + Integer.parseInt(pop())*(1<<i);
        }
        return dec;
    }

    /**
     * 二进制转八进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param bin
     * @return
     */
    String bin2otc(String bin) {
        for (int i=0;i<bin.length();i++) {
            element[i] = bin.charAt(i)+"";
            top++;
        }
        ArrayStack arrayStack = new ArrayStack(10);
        //pop()后top会变化
        //top转成doubl型
        double dTop = top/1.0;
        //向上取整
        double dTemp = Math.ceil(dTop/3.0);
        int temp = (int)dTemp;
        for (int j=0;j<temp;j++) {
            int otc = 0;
            //每3个二进制数计算出来就是八进制
            for (int i=0;i<3;i++) {
                //栈顶第1位*2^0+第2位*2^1+第3位*2^2........
                String popData = pop();
                //处理到最后栈可能不满3个数
                if (popData==null) {
                    break;
                }
                otc = otc + Integer.parseInt(popData)*(1<<i);
            }
            //计算出的八进制数压入新的栈里
            arrayStack.push(otc+"");
        }
        String strOtc = "";
        int tempTop = arrayStack.top;
        //新栈出栈组成八进制字符串
        for (int i=0;i<tempTop;i++) {
            strOtc+=arrayStack.pop();
        }
        return strOtc;
    }

    /**
     * 二进制转十六进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param bin
     * @return
     */
    String bin2hex(String bin) {
        for (int i=0;i<bin.length();i++) {
            element[i] = bin.charAt(i)+"";
            top++;
        }
        ArrayStack arrayStack = new ArrayStack(20);
        //pop()后top会变化
        //top转成doubl型
        double dTop = top/1.0;
        //向上取整
        double dTemp = Math.ceil(dTop/4.0);
        int temp = (int)dTemp;
        for (int j=0;j<temp;j++) {
            int hex = 0;
            //每4个二进制数计算出来就是十六进制
            for (int i=0;i<4;i++) {
                //栈顶第1位*2^0+第2位*2^1+第3位*2^2........
                String popData = pop();
                //处理到最后栈可能不满4个数
                if (popData==null) {
                    break;
                }
                hex = hex + Integer.parseInt(popData)*(1<<i);
            }
            //计算出的十六进制数压入新的栈里
            switch (hex) {
                case 10 : arrayStack.push("A");
                    break;
                case 11 : arrayStack.push("B");
                    break;
                case 12 : arrayStack.push("C");
                    break;
                case 13 : arrayStack.push("D");
                    break;
                case 14 : arrayStack.push("E");
                    break;
                case 15 : arrayStack.push("F");
                    break;
                default : arrayStack.push(hex + "");
                    break;
            }
        }
        String strHex = "";
        int tempTop = arrayStack.top;
        //新栈出栈组成十六进制字符串
        for (int i=0;i<tempTop;i++) {
            strHex+=arrayStack.pop();
        }
        return strHex;
    }

    /**
     * 八进制转十进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param otc
     * @return
     */
    int otc2dec(String otc) {
        for (int i=0;i<otc.length();i++) {
            element[i] = otc.charAt(i)+"";
            top++;
        }
        int dec = 0;
        //pop()后top会变化
        int temp = top;
        for (int i=0;i<temp;i++) {
            //栈顶第1位*8^0+第2位*8^1+第3位*8^2........
            //1<<3表示8, 每<<(i-1)*3表示乘以8
            if (i==0) {
                //第一次循环直接取出数据
                dec = dec + Integer.parseInt(pop());
            } else {
                //其他循环
                dec = dec + Integer.parseInt(pop())*((1<<3)<<((i-1)*3));
            }
        }
        return dec;
    }

    /**
     * 十六进制转十进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param hex
     * @return
     */
    int hex2dec(String hex) {
        for (int i=0;i<hex.length();i++) {
            element[i] = hex.charAt(i)+"";
            top++;
        }
        int dec = 0;
        //pop()后top会变化
        int temp = top;
        for (int i=0;i<temp;i++) {
            int intHex = 0;
            String strHex = pop();
            switch (strHex) {
                case "A" : intHex = 10;
                    break;
                case "B" : intHex = 11;
                    break;
                case "C" : intHex = 12;
                    break;
                case "D" : intHex = 13;
                    break;
                case "E" : intHex = 14;
                    break;
                case "F" : intHex = 15;
                    break;
                default : intHex = Integer.parseInt(strHex);
                    break;

            }


            //栈顶第1位*16^0+第2位*16^1+第3位*16^2........
            //1<<4表示16, 每<<(i-1)*4表示乘以16
            if (i==0) {
                //第一次循环直接取出数据
                dec = dec + intHex;
            } else {
                //其他循环
                dec = dec + intHex*((1<<4)<<((i-1)*4));
            }
        }
        return dec;
    }

    /**
     * 十进制转二进制
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param dec
     * @return
     */
    String dec2bin(int dec) {

        //退出条件:被除数一直除以2直到被除数变为0
        while ((dec)!=0){
            //除以2后的余数(除以2意味着位运算向右移动1位,
            // 移出去的0或者1就是余数,公式为：X & (2^N - 1))
            //1011 & 0001 = 1; 1即为余数
            int remainder = dec&(1);
            //被除数除以2
            dec = dec>>1;
            element[top++] = remainder + "";
        }
        String strBin = "";
        int temp = top;
        for (int j=0;j<temp;j++) {
            strBin += pop();
        }
        return strBin;
    }

    /**
     * 十进制转八进制
     * //十进制转十六进制同理可得
     * //(1111111101101)B == (8173)D == (17755)O == (1FED)H
     * @param dec
     * @return
     */
    String dec2otc(int dec) {

        //退出条件:被除数一直除以8直到被除数变为0
        while ((dec)!=0){
            //除以8后的余数(除以8意味着位运算向右移动3位,
            // 移出去的三位就是余数,公式为：X & (2^N - 1))
            //1011 & 0111 = 011; 011即为余数
            int remainder = dec&(7);
            //被除数除以8
            dec = dec>>3;
            element[top++] = remainder + "";
        }
        String strBin = "";
        int temp = top;
        for (int j=0;j<temp;j++) {
            strBin += pop();
        }
        return strBin;
    }


    /**
     * 输入一组括号,判断是否合法
     * ([[]])[(()())]
     * @param s
     * @return boolean
     */
    boolean isLegal (ArrayStack arrayStack1, ArrayStack arrayStack2, String s) {
        for (int i=0;i<s.length();i++) {
            arrayStack1.push(s.charAt(i)+"");
        }
        //循环遍历栈一
        while (!arrayStack1.isEmpty()) {
            String s1 = arrayStack1.pop();
            //如果是栈顶是右括号, 则进栈二
            if (s1.equals(")") || s1.equals("]")) {
                arrayStack2.push(s1);
            } else {
                //如果是左括号,则栈二的栈顶元素出栈,并与栈一的栈顶元素比较是否匹配
                String s2 = arrayStack2.pop();
                //如果匹配再进行下一轮循环, 不匹配则返回false
                if ((s1.equals("(") && s2.equals(")"))
                        || (s1.equals("[") && s2.equals("]"))) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }


    /**
     * 中缀表达式转后缀表达式(逆波兰表达式)
     * (3+4)*5-6  --->  34+5*6-
     * @param s
     */

    String nifix2postfix (String s) {
        Stack<Character> stack = new Stack<>();
        char toPush;
        String s1 = "";
        for (int i=0;i<s.length();i++) {
            toPush = s.charAt(i);
            //数字直接连接到字符串后
            if (toPush > 47 && toPush < 58) {
                s1 += toPush;
            } else {    //符号+-*/()需要入栈
                //处理+-操作符
                if (toPush == '+' || toPush == '-') {
                    //如果当前栈为空或栈顶为(,直接入栈
                    if (stack.isEmpty() || stack.peek()=='(') {
                        stack.push(toPush);
                    } else {
                        //一直循环pop,直到栈为空或者栈顶为(则退出
                        while (!stack.isEmpty() && stack.peek()!='(') {
                            s1 += stack.pop();
                        }
                        //新符号压入栈
                        stack.push(toPush);
                    }
                }
                //处理*/操作符
                if (toPush == '*' || toPush =='/') {
                    //如果栈为空或栈顶符号优先级小于toPush则直接入栈
                    if (stack.isEmpty() || stack.peek() == '+'
                            || stack.peek() == '-' || stack.peek() == '(' ) {
                        stack.push(toPush);
                    } else {
                        //如果栈不为空并且栈顶优先级等于toPush则循环出栈
                        //直到遇到+-(则退出循环
                        while (!stack.isEmpty()
                                && (stack.peek()=='*' || stack.peek()=='/')) {
                            s1 += stack.pop();
                        }
                        stack.push(toPush);
                    }
                }
                //处理(操作符
                if (toPush == '(') {
                    stack.push(toPush);
                }
                //处理)操作符
                if (toPush == ')') {
                    while (stack.peek()!='(') {
                        s1 += stack.pop();
                    }
                    //最后弹出(,扔掉
                    stack.pop();
                }
            }
        }
        //弹出栈中剩余符号放在最后
        while (!stack.isEmpty()) {
            s1 += stack.pop();
        }
        return s1;
    }

    /**
     * 后缀表达式计算(10以内的)
     * @param s
     */
    int postfixCompute (String s) {

        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            //根据ASCII码判断是否是数字
            //如果是数字,则入栈
            if (c>47 && c<58) {
                stack.push(Integer.parseInt(c+""));
            } else {
                //栈顶元素
                Integer i1 = stack.pop();
                //新的栈顶元素
                Integer i2 = stack.pop();
                switch (c) {
                    case '+' : result = i2 + i1;
                        //计算出的新结果入栈
                        stack.push(result);
                        break;
                    case '-' : result = i2 - i1;
                        stack.push(result);
                        break;
                    case '*' : result = i2 * i1;
                        stack.push(result);
                        break;
                    case '/' : result = i2 / i1;
                        stack.push(result);
                        break;

                }
            }
        }
        return result;
    }




    public static void main(String[] args) {
        ArrayStack arrayStack1 = new ArrayStack(50);
//        ArrayStack arrayStack2 = new ArrayStack(50);
//        System.out.println(arrayStack1.isLegal(arrayStack1, arrayStack2, "(([[]])[(([])())])"));
        System.out.println(arrayStack1.nifix2postfix("(3+4)*5-6"));
        System.out.println(arrayStack1.postfixCompute("123*+45*6+7*+"));

    }
}
