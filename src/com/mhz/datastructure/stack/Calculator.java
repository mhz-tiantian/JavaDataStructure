package com.mhz.datastructure.stack;

//计算 表达式  7*2*2-5+1-5+3-4

/**
 *  思路
 *  1. 通过index 值 来遍历我们的表达式
 *  2. 如果发现是数字, 则直接入数栈
 *  3. 如果发现扫描到是一个符号,则分如下情况
 *      3.1 如果发现当前的符号栈为空, 则直接入栈,
 *      3.2 如果发现符号栈中有操作符,就进行比较, 如果当前的操作符优先级小于或者等于栈中的
 *      操作符,就需要从数栈中pop出两个数 在从符号栈中pop一个符号, 进行运算得到的结果入数栈
 *      然后将当前的操作符入符号栈,
 *      3.3 如果当前的操作符优先级大于栈中的操作符,就直接入符号栈
 *   4. 当表达式扫描完毕, 就顺序从数栈和符号栈中pop出相应的数和符号, 并进行运算, 运算的结果入数栈
 *   5. 当数栈中只有一个数字, 就是表达式的结果
 *
 */
public class Calculator {

    public static void main(String[] agrs) {
        String expression = "7*20*2-5+1-5+3-4";
         //创建两个栈 ,一个数栈, 一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = ""; // 用于拼接多位数
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            // 当前扫描到的是符号
            if (operStack.isOper(ch)) {
                // 扫描到的是符号
                if (operStack.isEmpty()) {
                    // 符号栈为空, 直接入符号栈
                    operStack.push(ch);
                } else {
                    // 符号栈不为空  判断当前的符号, 优先级跟符号栈  栈顶的符号优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        //把当前的运算的结果入数栈
                        numStack.push(res);
                        // 然后把当前的操作符 入符号栈
                        operStack.push(ch);

                    } else {
                        // 当前的符号  优先级大于当前符号栈中栈顶的符号
                        operStack.push(ch);
                    }
                }

            } else {
                 // 扫描到的是一个数字, 但是需要考虑到多位数的 问题, 如果是多位数怎么解决呢?
                // 数字
//                numStack.push(ch - 48);
                // 处理多位数 思路
                //              1.当处理多位数时, 不能发现一个数就立即入栈,因为可能是多位数
                //              2. 当扫描到是一个数字时, 我们需要在往下在看一位, 如果是符号,就把当前的
                // 数字入数字栈, 如果是数字,就不管,然继续往下扫描
                //              3. 因此我们需要定义一个变量 ,用于拼接
                keepNum += ch;
                if (index == expression.length() - 1) {
                    //  如果是最后一位, 直接入数栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 注意一点是看后一位, 不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //将keepNum 清空
                        keepNum = "";
                    }
                }




            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        while (true) {
            if (operStack.isEmpty()) {
                // 如果符号栈为空, 则计算到最后的结果,数栈中只有一个数字[结果]
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            // 结果入数栈
            numStack.push(res);

        }
        int res2 = numStack.pop();
        System.out.printf("表达式的结果为%d", res2);
    }
}

class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组, 数据放在数组里
    private int top = -1; // 表示栈顶, 初始化为 -1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];

    }


    // 可以返回的当前栈顶的值, 但是不是真的pop
    public int peek() {
        return stack[top];
    }

    //栈满的判断
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈是不是空的
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈 - push
    public void push(int value) {
        if (isFull()) {
            System.out.println("沾满-------");
            // 栈满的时候  记得return 回去
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据了-------");
        }
        int value = stack[top];
        top--;
        return value;
    }


    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据了-------");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';

    }

    // 返回运算符的优先级, 优先级是程序员来确定的, 优先级使用数字表示
    // 数字越大, 则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式 只有+- */
        }

    }

    // 计算结果
    public int cal(int num1, int num2, int oper) {
        int res = 0;// 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }

        return res;
    }


}
