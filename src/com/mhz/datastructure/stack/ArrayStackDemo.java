package com.mhz.datastructure.stack;


import java.util.Scanner;

/**
 * 栈的介绍
 * 1. 栈是一个先入后出的有序列表
 * 2. 栈(stack)是限制线性表中元素的插入和删除, 只能在线性表的同一端进行的一种特殊的线性表
 * 允许删除添加的一端 为变化的一端 称为栈顶(Top) 另一端为固定的一端 称为栈底(Bottom)
 * 3. 根据栈的定义可知, 最先放入栈中的元素是在栈底, 最后放入的元素在栈顶, 而删除元素的时候  刚好相反,
 * 最先放入的(bottom)先删除, 最后放入的元素(top)先删除
 * <p>
 * <p>
 * <p>
 * <p>
 * 栈的应用场景
 * 1. 子程序的调用, 在调往子程序钱, 会先将下个指令的地址放在堆栈中, 直到子程序执行完后再将地址取出,
 * 以回到原来的程序中
 * 2. 处理递归调用, 和子程序的调用类似 , 只是除了储存下一个指令的地址外, 也将差数, 区域变量等数据一块存入堆栈中
 * 3. 表达式的转换(中缀表达式转后缀表达式)与求值
 * 4. 二叉树的遍历
 * 5. 图形的深度优化(depth - first)搜索法
 */

//用数据模拟栈的使用
public class ArrayStackDemo {


    public static void main(String[] agrs) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show :表示显示栈");
            System.out.println("exit :退出程序");
            System.out.println("push :表示添加数据到栈(入栈)");
            System.out.println("pop :表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出, 欢迎下次使用-----");
    }
}

class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组, 数据放在数组里
    private int top = -1; // 表示栈顶, 初始化为 -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];

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


}
