package com.mhz.datastructure.hashtab;

import java.util.Scanner;

/**
 * 哈希表(散列)
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的 id 时,
 * 要求查找到该员工的 所有信息.
 * <p>
 * 要求:1, 不使用数据库, 速度越快越好
 * 2.添加时,保证按照id从低到高插入
 * 3. 使用链表来实现哈希表,该链表不带表头
 * [即:链表的第一个节点就存放雇员信息]
 */
public class HashTabDemo {

    public static void main(String[] agrs) {
        HashTab hashTab = new HashTab(7);
        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add :添加雇员");
            System.out.println("list :显示雇员");
            System.out.println("find :查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名称");
                    String name = scanner.next();
                    // 创建雇员信息
                    Emp emp = new Emp(id, name);
                    hashTab.addEmp(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    Emp emp1 = hashTab.findEmpById(id);
                    if (emp1 != null) {
                        System.out.println(emp1.toString());
                    }
                    break;
                case "exit":
                    scanner.close();
                    // 退出程序
                    System.exit(0);
                    break;
            }
        }

    }
}


class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示多少条链表

    private int hashFun(int id) {
        return id % size;
    }

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //?这里确实是一个坑, empLinkedListArray 是初始化了, 但是
        // empLinkedListArray 这个里面的值确实没有初始化过, 所以会做成空指针异常
        EmpLinkedList linkedList = null;
        for (int i = 0; i < size; i++) {
            linkedList = new EmpLinkedList();
            empLinkedListArray[i] = linkedList;
        }
    }

    // 添加雇员
    public void addEmp(Emp emp) {
        // 根据员工的id， 得到该员工应当添加到那条链表中
        int no = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[no].add(emp);
        System.out.println("添加成功");

    }

    public Emp findEmpById(int id) {
        Emp emp = empLinkedListArray[hashFun(id)].findEmpById(id);
        return emp;

    }

    // 遍历所有的链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list();
        }
    }
}

// 创建EmpLinkedList表示链表
class EmpLinkedList {
    // 头部节点, // 默认为null
    private Emp head;


    // 根据id查找雇员，
    // 如果查找到，就返回emp， 如果没有找到， 就返回null
    public Emp findEmpById(int no) {
        if (head == null) {
            System.out.println("当前的链表为空, 无法查找雇员信息");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp == null || curEmp.id == no) {
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 遍历当前链表雇员的信息
     */
    public void list() {
        if (head == null) {
            System.out.println("当前链表为空-----");
            return;
        }
        Emp curEmp = head;
        while (true) {
            // 打印的这句话, 要放在前面, 不然 第一个的时候容易为空
            System.out.println("链表中雇员的信息为" + curEmp.toString());
            if (curEmp.next == null) {
                break;
            }
            // 记得一定要后羿， 不然死循环
            curEmp = curEmp.next;
        }
    }


    // 添加雇员的信息
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            // 后移一位
            curEmp = curEmp.next;
        }
        // 添加雇员信息到链表上
        curEmp.next = emp;


    }
}


/**
 * 雇员的类
 */
class Emp {
    public int id;
    public String name;
    // 下一个雇员
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
