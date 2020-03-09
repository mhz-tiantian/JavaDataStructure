package com.mhz.datastructure.linkedlist;

public class SingleLinkedListDemo {

}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");
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

    }

}

