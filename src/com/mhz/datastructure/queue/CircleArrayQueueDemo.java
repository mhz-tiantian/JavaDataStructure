package com.mhz.datastructure.queue;

import com.sun.org.apache.xpath.internal.axes.OneStepIteratorForward;
import sun.rmi.runtime.Log;

import java.time.OffsetDateTime;
import java.util.Scanner;

//对前面的数据模拟队列的优化, 充分利用数据, 因此将数据看做是一个环形的(通过取模的方式来实现即可)
public class CircleArrayQueueDemo {
    // 思路
    // 1.尾索引的下一个为头索引的时表示队列满. 即将队列容器空出一个作为约定,
    // 队列满的条件为

    public static void main(String[] agrs) {

        CircleArray queue = new CircleArray(4);
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
    }
}


class CircleArray{
    private int maxSize; // 最大的容量
    private int front; // 队列的第一个元素 初始化为0
    private int rear; // 队列的最后一个元素 初始化为0
    private int[] arr;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    // 判断队列是否满
    public  boolean isFull(){
        return (rear + 1) % maxSize == front;
    }
    // 判断队列是否为空
    public  boolean isEmpty() {
        return rear == front;
    }
    // 添加数据到队列
    public  void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满, 不能添加数据哦------");
            return;
        }
        // 先加入数据到队列
        arr[rear] = n;
        // real 后移,. 这里必须考虑取模  这里注意 如果当加入一个数据后, 变量会改变
        rear = (rear + 1) % maxSize;
    }


    public  int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 不能取数据哦-------");
        }
        // 因为front是指向队列的第一个元素
        // 1. 先把front对应的值保存到一个临时变量
        // 2.将front后移, 考虑取模
        //3. 把临时变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 打印出当前队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的, 没有数据哦-----");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    // 这里只是查看数据, 并不是取过去, 所以不为front的值做后移操作
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空, 没有数据哦---------");
        }
        return arr[front];
    }

    // 得到当前队列的有效个数
    public  int  size(){
        // rear=2
        // front=1
        // maxsize=4
        System.out.printf("rear====%d\n front===============%d", rear, front);
        System.out.printf("队列的有效个数为" + (rear + maxSize - front) % maxSize);
        return (rear + maxSize - front) % maxSize;

    }
}

