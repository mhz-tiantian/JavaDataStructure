package com.mhz.datastructure.linkedlist;

import java.util.Currency;

//  问题为：设编号为 1，2，… n 的 n 个人围坐一圈，
//  k（1<=k<=n）的人从 1 开始报数，数到
//m 的那个人出列，它的下一位又从 1
// 开始报数，数到 m 的那个人又出列，依次类推，
// 直到所有人出列为止，由此
//产生一个出队编号的序列。
public class Josepfu {

    public static void main(String[] agrs) {
        CircleSingleLinkedList circleList = new CircleSingleLinkedList();
        circleList.addBody(125);
        circleList.showBoy();
        circleList.countBoy(10, 6, 125);
    }




}

class CircleSingleLinkedList{
    private Boy first = null;

    // 添加小孩节点, 构建成一个环形的链表
    public void addBody(int nums) {
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        // 辅助指针, 帮助构建环形链表
        Boy curBoy = null;
        for (int i = 1; i <=nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);//构建成环形
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                // curboy指针后移
                curBoy = boy;
            }
        }

    }

    public  void showBoy() {
        if (first == null) {
            System.out.println("没有任何的小孩-----");
            return;
        }
        //first指针不能用, 所以需要一个辅助指针,来完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("当前小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            // 让curBoy后移,
            curBoy = curBoy.getNext();

        }
    }

    /**
     * 根据用户的输入,计算出小孩的出圈的顺序
     * @param startNo 从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums 最初由多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先做数据的校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误, 请重新输入");
            return;
        }
        //创建一个辅助指针, 帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针(helper)事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();

        }
        // 小孩报数前, 先让frist 和helper移动startNo-1次
        for (int j = 0; j < startNo - 1; j++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        // 当小孩报数时, 让frist和helper 指针同时移动countNum-1次, 然后出圈
        while (true) {
            if (helper == first) {
                // 说明圈中只有一个节点 退出循环
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时候, first指针就是指向要出圈的小孩
            System.out.printf("小孩%d 出圈\n", first.getNo());

            // 出圈操作
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());


    }

}



class  Boy{

    public Boy(int no) {
        this.no = no;
    }

    // 小孩的编号
    private int no;
    // 指向下一个节点
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
