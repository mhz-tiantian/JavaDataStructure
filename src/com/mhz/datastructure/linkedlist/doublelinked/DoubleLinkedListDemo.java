package com.mhz.datastructure.linkedlist.doublelinked;


/**
 *  双向链表的增删改查
 */
public class DoubleLinkedListDemo {


    public  static  void main(String [] agrs){
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        // 显示一把
        doubleLinkedList.showList();

        HeroNode2 newHeroNode = new HeroNode2(2, "小卢", "玉麒麟---");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况----");
        doubleLinkedList.showList();
        // 删除一个节点
        doubleLinkedList.del(1);
        doubleLinkedList.del(4);
        System.out.println("删除后的链表情况----");
        doubleLinkedList.showList();
    }








}
class  DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");


    /**
     *  根据节点的heroNode2的编号来添加节点, 如果编号存在, 提示编号已经存在
     * @param heroNode2
     */
    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 到链表的最后 直接返回
                break;
            }
            if (temp.next.no > heroNode2.no) {
                break;
            }
            if (temp.next.no == heroNode2.no) {
                //编号存在 直接返回了
                flag = true;
                break;
            }
            temp = temp.next;

        }
        // 必须要在while循环的后面
        if (flag) {
            System.out.printf("编号已经存在%d", heroNode2.no);
        } else {
            heroNode2.pre = temp;
            if (temp.next != null) {
                temp.next.pre = heroNode2;
            }
            heroNode2.next = temp.next;
            temp.next = heroNode2;
        }
    }


    /**
     *根据编号 删除节点
     * @param no
     */
    public void del(int no) {
        // 需要要找到需要删除的节点, 可以参考更新的
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.printf("要删除的节点不存在,%d\n", no);
        }
    }






    public void update(HeroNode2 heroNode2) {
        // 首先要找到需要更新的节点
        HeroNode2 temp = head.next;
        boolean flag = false; // flag 为true的时候就找到了, 当前需要更新的节点
        while (true) {
            if (temp == null) {
                // temp为空的时候表示链表已经到最后的了, 没有下一个节点了, 就需要返回了
                break;
            }
            if (temp.no == heroNode2.no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            temp.nickName = heroNode2.nickName;
            temp.name = heroNode2.name;
        } else {
            System.out.printf("没有找到需要更新的节点编号为%d", heroNode2.no);
        }

    }



    public  HeroNode2 getHead(){
        return head;
    }
    //遍历双向链表的方法
    public  void showList(){
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 头结点不能动, 所以需要一个辅助变量来遍历 (指针指向 head的下一个节点)
        HeroNode2 temp = head.next;
        while (true) {
            // 到链表的最后了 , 跳出循环
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // 将temp后移,
            temp = temp.next;
        }
    }

    // 添加一个节点到双向链表的最后(默认添加到最后)
    public  void add(HeroNode2 heroNode) {
        // 1. 首先先找到链表的最后
        HeroNode2 temp = head;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 没有找到最后, 将temp后移
            temp = temp.next;
        }
        // 当退出循环的时候, temp就指向了链表的最后
        // 添加节点到temp的后面, 因为是双向链表  所以最后的pre 需要指向temp
        temp.next = heroNode;
        heroNode.pre = temp;

    }


}
