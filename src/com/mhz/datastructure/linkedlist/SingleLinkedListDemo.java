package com.mhz.datastructure.linkedlist;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] agrs) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);

        // 显示一把
        singleLinkedList.showLinkedList();

        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟---");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况----");
        singleLinkedList.showLinkedList();
        // 删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
        System.out.println("删除后的链表情况----");
//        singleLinkedList.showLinkedList();
        HeroNode heroNode = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.printf("找到链表倒数第 %d的信息为%s\n", 2, heroNode);
    }

    //获取单链表的节点个数
    public static int getLength(HeroNode head) {
        int length = 0;
        if (head.next == null) {
            // 空链表 直接返回0
            return length;
        }
        //不包括头结点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }


    // 查找单链表中的倒数第k个结点[新浪面试题]
    // 思路
    // 首先2个差数 接收 head节点, 接收index
    //先把链表从头到尾遍历, 得到链表 的总长度 getlength()
    // 得到size后, 我们从链表的第一个开始遍历(size -index)个,
    //如果找到了. 则返回该节点, 否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 如果链表为空, 返回null
        if (head.next == null) {
            //没有找到
            return null;
        }
        int size = getLength(head);
        // 先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助指针
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    // 将单链表反转
    public  static  void reversetList(HeroNode head) {
        //如果链表为空, 或者只有一个节点的时候, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode formerNext = null;
        HeroNode reversetHeroNode = new HeroNode(0, "", "");
        while (cur != null) {
            formerNext = cur.next;
            cur.next = reversetHeroNode.next; // 将cur的下一个节点指向新的链表的最前端
            reversetHeroNode.next = cur; //将cur链接到新的链表上
            cur = formerNext;
        }
        // 将head.next 指向reverseHead.next 实现单链表的反转
        head.next = reversetHeroNode.next;


    }


    // 4. 从尾到头打印单链表 百度[1. 反向遍历, 2. 利用stack 栈这个数据结构]
    public static  void reversePrint(HeroNode head) {
        if (head.next == null) {
            // 空链表, 不打印
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            // stack的特点 是先进后出
            System.out.println(stack.pop());
        }
    }
    // 5. 合并2个有序的单链表, 合并之后链表依然有序(课后题)


}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");


    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode){
        // 因为head节点不能动, 因此我们需要一个变量辅助遍历
        HeroNode temp = head;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            temp = temp.next;

        }
        // 当while循环推出是， temp就指向了链表的最后
        // 1. 然后 我们把链表的最后添加 需要添加的节点
        temp.next = heroNode;

    }

    // 第二种添加英雄方式， 根据排名将英雄插入到指定的位置
    // 如果有 这个排名， 则添加失败，并给出提示
    public  void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 说明已经到链表的最后了
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到了, 就在temp的后面
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明希望添加的heroNode的编号已经存在了
                flag = true;
                break;
            }
            temp = temp.next; // 后移 遍历当前链表

        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            // 注意这里的顺序不能改 .不然是不对的
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    // 修改节点新, 根据no编号来修改, 即no编号不能改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空----");
            return;
        }
        // 找到需要修改的节点, 根据no编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //已经遍历完了
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            // 如果没有找到后移
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号 %d的节点,不能修改\n", newHeroNode.no);
        }
    }

    // 删除节点
    // 思路 找到待删除的节点的上一个节点
    //1 head不能动, 因此我们需要一个temp辅助节点找到待删除节点
    // 2.说明我们在比较是, 是temp.next.no和需要删除的节点的

    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到删除的节点 temp.next
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 删除节点
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d  节点不存在\n", no);
        }

    }

    // 显示链表(遍历)
    public  void showLinkedList(){
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        // 没有遍历头节点
        while (true) {
            // 遍历完成, 退出循环
            if (temp == null) {
                break;
            }
            // 输出信息
            System.out.println(temp);
            //将temp后移, 一定小心
            temp = temp.next;

        }

    }




}

