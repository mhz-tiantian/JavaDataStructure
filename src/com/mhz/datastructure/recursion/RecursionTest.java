package com.mhz.datastructure.recursion;

/**
 * 递归的测试
 * 1. 递归的概念
 * 简单的说, 递归就是自己调用自己, 每次调用时传入不同的变量, 递归有助于编程者解决复杂的问题, 同时可以让代码变得简洁
 * <p>
 * 递归的调用机制
 * 1.打印问题
 * 2. 阶乘问题
 * 3.递归调用规则: 1.当程序执行到一个方法时, 就会开辟一个独立的空间(栈)
 * 2. 每个空间的数据(局部变量), 是独立的,
 * 3. 对于引用类型的数据 (因为引用类型的对象是在堆里面的),所以一个空间的数, 改变了, 其他的空间也会改变
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 递归可以解决的问题
 * 1. 各种数学的问题 如:皇后问题, 汉诺塔. 阶乘问题, 迷宫问题, 球和篮子的问题
 * 2. 各种算法中也会使用到递归 如快排, 归并排序, 二分查找, 分治算法
 * 3. 将用栈解决的问题,----> 递归代码比较简洁
 * <p>
 * 递归需要遵守的重要规则
 * 1. 执行一个方法时, 就创建一个新的受保护的独立空间(栈空间)
 * 2. 方法的局部变量是独立的,不会相互影响, 比如n变量
 * 3. 如果方法中使用的是引用类型变量 (如果数组), 就会共享该引用类型的数据
 * 4. 递归必须向退出递归的条件逼近, 否则就是无限递归, 出现StackOverflowError
 * 5. 当一个方法执行完毕, 或者遇到return 就会返回, 遵守谁调用, 就将结果返回给谁,
 * 同时当方法执行完毕或者返回时, 该方法也就执行完毕
 */
public class RecursionTest {


    public static void main(String[] agrs) {
        int n = 5;
        test(n);
        // 输入结果  2   3  4
        // 输入的结果  解释， 1， 当n=4 的时候， n>2 成立  所以后面的代码, 暂时不能执行  再次调用test (4-1 )
        // 2, 所以 test(3)的时候 n>2成立  所以后面的代码, 暂时不能执行, 再次调用 test(3-1)
        // 3. test(2) 的时候n>2 不成立, 先执行 System.out.println("n===" + n);
        // 输出 n=2,  然后回溯 回到test(3)的空间栈去执行 System.out.println("n===" + n);
        // 输出 n=3 , 然后回溯, 回到test(4)的空间栈去执行 System.out.println("n===" + n);
        // 输出 n=4 ,然后程序执行完毕, 结束
//        int total = factorial(7);
//        System.out.println("total====" + total);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n===" + n);
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            System.out.println("n - 1========" + (n - 1));
            System.out.println("n " + n);
            return factorial(n - 1) * n;
        }

    }
}



