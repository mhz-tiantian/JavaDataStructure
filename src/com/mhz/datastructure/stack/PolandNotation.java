package com.mhz.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 1. 输入一个逆波兰表达式(后缀表达式).使用栈, 计算其结果
 * 2. 支持小括号,和多位数整数  只支持整数
 * 3. 思路分析  (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 *     1.从左到右扫描.将3和 4 压入堆栈
 *     2.遇到 + 运算符, 因此弹出4和3( 4为栈顶元素, 3为次顶元素), 计算出 3+4 的值,得7 再将7 入栈;
 *     3将5入栈
 *     4. 接下来是 × 运算符, 因此弹出 7 和5 计算出 7 × 5 =35 将35入栈
 *     5. 将6 入栈'
 *     6 最后是 -运算符, 计算出 35 -6 =29 因此得出最终的结果
 *
 *
  */


public class PolandNotation {

    public  static  void main(String  [] agrs) {
        //String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
        System.out.printf("表达式的结果为%d\n", cal(getListString(suffixExpression)));

    }

    /**
     *  计算后缀表达式的结果
     * @param suffixExpressionList
     * @return
     */
    public static int cal(List<String> suffixExpressionList) {
        Stack<String> stack = new Stack<>();
        for (String ele : suffixExpressionList) {
            // 正则表达式 ,如果是 0- 9  也可以是多位数
            if (ele.matches("\\d+")) {
                // 遇到是数字 不管其他的直接入栈
                stack.push(ele);
            } else {
                // 如果是计算符号, 就pop出来 2个数,计算, 计算结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (ele.equals("+")) {
                    res = num1 + num2;
                } else if (ele.equals("-")) {
                    res = num1 - num2;
                } else if (ele.equals("/")) {
                    res = num1 / num2;
                } else if (ele.equals("*")) {
                    res = num1 * num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }
        int result = Integer.parseInt(stack.pop());
        return result;
    }

    /**
     *  将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        if (!suffixExpression.contains(" ")) {
            return null;

        }
        List<String> result = new ArrayList<>();
        String [] spilt=suffixExpression.split(" ");
        for (String ele : spilt) {
            result.add(ele);
        }
        return result;
    }

}
