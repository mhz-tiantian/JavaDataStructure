package com.mhz.datastructure.tree;

import java.time.OffsetDateTime;

/**
 * 二叉树的结构
 * <p>
 * 为什么要使用树的结构
 * 1数组存储f方式的分析:
 * 优点:通过下标方式访问元素,速度快, 对于有序的数组,还可使用二分查找提高检查速度
 * 缺点:如果要检索具体某个值, 或者插入值(按一定顺序)会整体移动, 效率较低
 * <p>
 * 2.链式存储方式的分析
 * 优点:在一定程度上对数组存储方式有优化(比如:插入一个数组节点,只需要将插入节点,
 * 链表到链表中即可,删除效率也很好)
 * 缺点:在进行检索时, 效率仍然较低, 比如(检索某个值:需要从头节点开始遍历)
 * <p>
 * <p>
 * 树存储方式的分析
 * 能提高数据 存储  读取的效率, 比如利用二叉排序树(binary sort tree) 即可以保证数据的检索速度,
 * 同时也可以保证数据的插入, 删除, 修改的速度
 * <p>
 * 树的常用术语
 * 1 节点
 * 2. 根节点
 * 3.父节点
 * 4,子节点
 * 5 叶子节点(没有子节点的节点)
 * 6.节点的权(节点值)
 * 7. 路径(从root 节点找到该节点的路线)
 * 8 层
 * 9 子树
 * 10 树的高度(最大层数)
 * 11 森林 多颗子树构成森林
 * <p>
 * 二叉树的概念
 * 树有很多种, 每个节点最多只能有两个节点的一种形式称为二叉树
 * 二叉树分为右节点,左节点
 * <p>
 * 二叉树的遍历说明
 * 使用前序,中序, 后序 对下面的二叉树进行遍历
 * 前序遍历:先输出父节点, 再遍历左子树和右子树
 * 中序遍历 :先遍历左(left)子树,在输出父节点,在遍历右子树
 * 后序遍历 先遍历左(left)子树 在遍历右子树, 在输出父节点
 * <p>
 * <p>
 * 二叉树  查找指定节点
 * 1.请编写前序查找,中序查找, 后序查找的方法
 * 2.并分别使用  三种查找方式,查找heroNo=5的节点
 * 3,并分析各种查找方式,分别比较了多少次
 * 思路分析:
 * 1.前序查找思路
 * 1,先判断当前节点的no是否等于要查找的方法
 * 2.如果是相等, 则返回当前的节点
 * 3. 如果不等,则判断当前的节点的左子节点是否为空,
 * 如果不为空, 则递归前序查找
 * 4.如果左递归前序查找,找到节点,则返回,否则继续判断,
 * 当前的节点的右节点是否为空,
 * 如果不空,则继续向右递归前序查找
 * <p>
 * 2.中序查找思路
 * 1.判断当前节点的左子节点是否为空, 如果不为空, 则递归中序查找
 * 2. 如果找到, 则返回, 如果没有找到, 就和当前节点比较,
 * 如果是则返回当前节点, 否则继续进行右递归的中序查找
 * 3. 如果右递归中序查找, 找到就返回, 否则返回null
 * <p>
 * <p>
 * 3. 后序查找思路
 * 1. 判断当前节点的左子节点是否为空, 如果不为空, 则递归后序查找
 * 2. 如果找到,就返回, 如果没有找到, 就判断当前节点的右子节点是否为空,
 * 如果不为空,则右递归进行后序查找,如果找到,就返回
 * 3,就和当前节点进行比较, 如果是返回, 否则返回null
 */
public class BinaryTreeDemo {

    public static void main(String[] agrs) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, ":卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(node5);
        binaryTree.setRoot(heroNode1);
//        System.out.println("前序遍历");  //  1 ,2,3,4
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();// 2, 1,3,4
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder(); // 2,4,3,1

        System.out.println("前序查找----");
//        binaryTree.preOrderSearch(5);
        // 4次
        System.out.println("前序查找找到的节点为" + binaryTree.preOrderSearch(5));

        // 2次   2 跟5
        System.out.println("后序查找找到的节点为" + binaryTree.postOrderSearch(5));

        // 3次, 2, 1跟 5
        System.out.println("中序查找找到的节点为" + binaryTree.infixOrderSearch(5));

    }


}

// 二叉树的类
class BinaryTree {
    // 根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序查找 返回查找的节点
     *
     * @param no 查找节点的编号
     * @return 如果找到, 就返回查找的节点, 没有找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;

    }

    /**
     * 中序查找
     *
     * @param no 查找节点的编号
     * @return 如果找到, 就返回查找的节点, 没有找到就返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;

    }

    /**
     * 后序查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }


    // 后序遍历的方法
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }

    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }

    }
}

// 节点的类
class HeroNode {
    private int no;
    private String name;
    private HeroNode right;
    private HeroNode left;


    /**
     * 前序查找
     *
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
         System.out.println("---------前序查找---------");
        if (this.no == no) {
            return this;
        }
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(no);
        }
        return node;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * 编写后序遍历的方法
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 编写中序遍历的方法
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 编写前序遍历的方法
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }


    /**
     * 后序查找
     *
     * @param no 需要查找节点的编号
     * @return 如果找到返回节点 , 如果没有找到范湖null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);

        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("---------后序查找---------"+this.no);
        if (this.no == no) {
            node = this;
        }
        return node;

    }

    /**
     * 中序查找
     *
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {

        HeroNode node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
        }
        if (node != null) {
            return node;
        }
        System.out.println("---------中序查找---------" + this.no);
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);
        }
        return node;

    }
}
