package com.mhz.datastructure.stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 1. 输入一个逆波兰表达式(后缀表达式).使用栈, 计算其结果
 * 2. 支持小括号,和多位数整数  只支持整数
 * 3. 思路分析  (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * 1.从左到右扫描.将3和 4 压入堆栈
 * 2.遇到 + 运算符, 因此弹出4和3( 4为栈顶元素, 3为次顶元素), 计算出 3+4 的值,得7 再将7 入栈;
 * 3将5入栈
 * 4. 接下来是 × 运算符, 因此弹出 7 和5 计算出 7 × 5 =35 将35入栈
 * 5. 将6 入栈'
 * 6 最后是 -运算符, 计算出 35 -6 =29 因此得出最终的结果
 */


public class PolandNotation {

    public static void main(String[] agrs) {
        //String suffixExpression = "30 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
//        System.out.printf("表达式的结果为%d\n", cal(getListString(suffixExpression)));


        String middleExpression = "1+((2+3)*4)-5";
        List<String> middleExpressionList = toInfixExpressionList(middleExpression);
        System.out.printf("中缀表达式转成List=====>%s\n", middleExpressionList);

        List<String> ls = parseSuffixExpreesionList(middleExpressionList);
        System.out.printf("后缀表达式为=====>%s\n", ls);


        System.out.printf("计算的结果为=====>%d\n", cal(ls));

    }

    /**
     * 计算后缀表达式的结果
     *
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
                //结果入栈
                stack.push(String.valueOf(res));
            }
        }
        int result = Integer.parseInt(stack.pop());
        return result;
    }

    /**
     * 将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        if (!suffixExpression.contains(" ")) {
            return null;

        }
        List<String> result = new ArrayList<>();
        String[] spilt = suffixExpression.split(" ");
        for (String ele : spilt) {
            result.add(ele);
        }
        return result;
    }

    /**
     * 将一个中缀表达式， 依次将数据和运算符 放入到 ArrayList 中
     *
     * @param middleExpression 中缀表达式 例如:1+((2+3)×4)-5 ====>[1,+,(,(,2,+,3,),*,4,),-,5]
     * @return
     */
    public static List<String> toInfixExpressionList(String middleExpression) {
        List<String> ls = new ArrayList<>();
        int index = 0;
        String str; // 用于多位数的拼接
        char c;// 遍历到的每一个字符, 就放在c
        // do  while 循环, 先执行一次, 再去判断条件是否成立
        do {
            c = middleExpression.charAt(index);
            //根据ASCII吗对应表
            // 说明当前扫描到的不是一个数字 ,如果不是数字, 直接加入到List里面
            if (c < 48 || c > 57) {
                ls.add(c + "");
                // 加入完, 需要把index指针 后移
                index++;
            } else {
                //清空 应该放在外面
                str = "";
                // 说明现在是数字, 但是需要考虑到多位数的问题
                while (index < middleExpression.length() && ((c >= 48 && (c <= 57)))) {
                    str = str + c;
                    // 这里是对着的, 因为do  while 循环, 先执行一次, 再去判断条件是否成立
                    index++;
                    if (index < middleExpression.length()) {
                        // 这里需要把c 重新赋值
                        c = middleExpression.charAt(index);
                    }

                }
                ls.add(str);

            }


        } while (index < middleExpression.length());
        // 循环完, 就是需要做的
        return ls;


    }

    /**
     *  将得到的中缀表达式 转换成 后缀表达式的List
     * @param middleSuffixExpressionList 中缀表达式的list 1+((2+3)*4)-5
     * @return 后缀表达式的List    1 2 3 + 4 * + 5 -
     * 思路
     * 1.初始化两个栈, 运算符栈s1 ,和存储中间结果的栈s2
     * 2. 从左到右扫描中缀表达式
     * 3. 遇到操作数时,将其压入s2 中
     * 4. 遇到运算符是, 比较其与s1栈顶运算符的优先级
     *    1.如果s1为空, 或者栈顶的运算符为左括号"(" 则直接将此运算符入栈 s2
     *    2.否则, 若优先级比栈顶运算符的高(大于或者等于的时候),也将运算符压入s1 ;
     *    3.否则(优先级小于栈顶运算符的时候),将s1栈顶的运算符弹出并压入到s2中, 再次转到 4 1 与s1中新栈顶的运算符 比较
     * 5. 遇到括号是:
     *      1. 如果是左括号"("  则直接压入s1
     *      2. 如果是右括号")" 则依次弹出s1栈顶的 运算符,并压入s2,直到遇到左括号位置, 此时将这一对括号丢弃
     *
     *
     * 6. 重复步骤 2到5 直到表达式的最右边
     * 7 .将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出, 结果的逆序即为中缀表达式对应的后缀 表达式
     *
     *
     *
     *
     *
     */
    public static List<String> parseSuffixExpreesionList(List<String> middleSuffixExpressionList) {
        // 定义一个符号栈
        Stack<String> s1 = new Stack<>();
        //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的 Lists2

        for (String item : middleSuffixExpressionList) {
            // 如果当前的item的是数字
            if (item.matches("\\d+")) {
                // 添加到s2中
                s2.add(item);
            } else {
                // 如果s1 为空, 或者 栈顶为"("的时候  当前符号入符号栈 这里不能使用pop 弹出, 还是使用peek 查看下栈顶的元素
                if (s1.empty() || s1.peek().equals("(")) {
                    s1.push(item);
                }else if (item.equals("(")){
                    s1.push(item);
                }
                // 当前扫描到的右括号
                else if (item.equals(")")) {
                    //如果是右括号")" 则依次弹出s1栈顶的 运算符,并压入s2,直到遇到左括号位置, 此时将这一对括号丢弃
                    while (!s1.peek().equals("(")) {
                        s2.add(s1.pop());
                    }
                    // 去掉左括号"("
                    s1.pop();
                } else {
                    //(优先级小于栈顶运算符的时候),将s1栈顶的运算符弹出并压入到s2中
                    while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                        s2.add(s1.pop());
                    }
                    s1.push(item);

                }

            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;

    }


}

//编写一个类,Operation 可以返回一个运算符对应的优先级
class  Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        System.out.println("operation======" + operation);
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.printf("不存在改运算符");
                throw new RuntimeException("不存在该运算符");
        }
        return result;

    }


}
