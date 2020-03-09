package com.mhz.datastructure.queue;

import java.util.Scanner;

/**
 *   队列介绍
 *   1. 队列是一个有序列表,可以用数组或是链表来实现
 *   2. 遵循先入先出的原则, 即先存入队列的数据, 要先取出, 后存入的要后取出
 */
public class ArrayQueueDemo {

    //数组模拟队列思路
    // 1.队列本身是有序的列表,如果使用数组的结构来存储队列的数据, maxSize队列的最大容量
    //2. 因为队列的输出, 输入是分别从前后端来处理, 因此需要2个变量 front 以rear分别记录队列前后端的下标,
    // front会随着数据输出而改变, rear则是随着数据输入而改变
    // 3.当我们将数据存入队列时 "addQueue"
    // addQueue 方法思路分析
        //1. 将尾指针往后移rear+1. 当front ==rear 的时候为空
        // 2.当尾指针rear 小于队列的最大下标maxSize -1的时候可以存入数据,
        // 否则无法存入数据. rear==maxSize-1队列满


    public static void main(String[] agrs) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; // 接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                default:break;
                case 'h':
                    int headQueue = queue.headQueue();
                    System.out.printf("队列头的数据是%d\n", headQueue);
                    break;
                case 'g':
                    int res = queue.getQueue();
                    System.out.printf("队列取出来的数据是%d\n", res);
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }

        }
        //  模拟队列完成了, 但是我们发现, 以及是取出来数据,再去插入数据的时候,还是提示队列是满的, 就是说, 我们没有实现队列的复用,
        // 怎么显示复用了, 我们可以使用环形队列  环形队列核心 % 取模
    }



}

class ArrayQueue{
    private int front; //队列头
    private int rear;// 队列尾
    private int maxSize; // 最大的容量
    private int[] arr;// 数组, 存储的数据
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }
    // 队列是否满
    public  boolean isFull(){
        return rear == maxSize - 1;
    }
    // 队列是否为空
    public  boolean isEmpty(){
        return front == rear;
    }

    // 添加数据到队列
    public  void addQueue(int n) {
        // 1首先判断队列是否满
        if (isFull()) {
            System.out.println("------队列满,不能添加数据---------");
            return;
        }
        // 2. real后移
        rear++;
        arr[rear] = n;
    }
    // 获取队列的数据
    public  int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据-----");
        }
        front++; // front 后移
        return arr[front];
    }

    // 显示队列的所有数据
    public  void showQueue(){
        if (isEmpty()) {
            System.out.println("队列为空， 没有数据了-----");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }




    // 显示队列的头数据， 注意：只是显示，不是取数据
    public  int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据-----");
        }
        return arr[front + 1];
    }


}
