package com.mhz.datastructure.recursion;

/**
 * 8皇后的问题
 * <p>
 * 问题介绍
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于
 * 1848 年提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即： 任意两个皇后都不能处于同一行 、
 * 同一列或同一斜线上，问有多少种摆法(92)。
 * <p>
 * 思路分析
 * 1,第一个皇后先放第一行第一列
 * 2. 第二个皇后放在第二行 第一列,判断是否ok , 如果不ok, 继续放第二行 第二列 第三列.
 * 依次把所有列都放完, 找到一个合适的
 * 3. 继续第三个皇后, 还是第一列 , 第二列 .直到第8个皇后 也能放在一个不冲突的位置, 算是找到一个正确解
 * 4. 当得到一个正确解时, 在栈回退到上一个栈时, 就会开始回溯, 即将第一个皇后, 放到第一列的所有正确解
 * 全部得到
 * 5. 然后回头继续第一个皇后放第二列, 后面继续循环执行  1 2 3 4 的步骤
 * <p>
 * <p>
 * 说明, 理论上应该创建一个二维数组来表示棋盘, 但是实际上可以通过算法,用一个一维数组 即可解决问题,
 * arr[8]={0,4,7,5,2,6,1,3}  对应arr 下标表示第几行 , 即第几个皇后
 * arr[i]=val , val 表示第i+1 个皇后, 放在第 i+1行的 第 val +1 列
 */
public class Queue8 {
    // 定义一个max 表示共有多少个皇后
    int max = 8;
    // 定义一个数组, 保存皇后放置位置的结果  比如 arr={0,4,7,5,2,6,1,3}
    int[] array = new int[8];
    static int count = 0;


    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一总有%d解法", count);
    }

    //编写一个方法, 放置第n个皇后
    // 注意:check是第一次递归时, 进入到check中都有
    // for(int i=0;i<max;i++) 因此会有回溯
    private void check(int n) {
        if (n == max) {
            // 如果n==8的時候， 表示第8個皇后已经放好了.  直接打印返回
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n 放在该行的第1列
            array[n] = i;
            // 判断是否冲突, 如果不冲突 接着放n+1个皇后, 开始递归
            if (judge(n)) {
                check(n + 1);
            }
            // 如果冲突, 就继续执行array[n]=i;就是第n个皇后,放置在本行得  后移一个位置
        }

    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

    }


    /**
     * 查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return false 表示冲突, true表示不冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 1.array[i]==array[n]表示判断第n个皇后是否和前面的n-1个皇后在同一列
            // Math.abs(int ) 返回int的绝对值
            // 2. Math.abs(n-i)==Math.abs(array[n]-array[i]) 判断第n个皇后,是否和第i皇后是否在同一个斜线上

            // 3. 判断是否在同一行 , 没有必要, n每次都在递增
            if (array[i] == array[n] || (Math.abs(n - i) == Math.abs(array[n] - array[i]))) {
                return false;
            }
        }
        return true;

    }

}
