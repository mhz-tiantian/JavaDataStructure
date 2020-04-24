package com.mhz.datastructure.search;

import java.util.Arrays;

/**
 * 查找算法
 * 在java 中, 我们常用的查找有四种:
 * 1.顺序(线性)查找
 * 2.二分查找/ 这班查找
 * 3. 插值查找
 * 4. 斐波那契查找
 */
// 线性查找算法
public class SeqSearch {
    // 需求:有一个数列{1,8,10,89,1000,1234},
    // 判断数列中是否包括此名称[顺序查找,]
    // 要求:如果找到了,就提示找到了, 并给出下标值

    public static void main(String[] args) {
//        int[] arr = {1, 8, 10, 89, 1000, 1234};
//        int index = seqSearch(arr, 1000);
//        if (index == -1) {
//            System.out.println("没有找到");
//        } else {
//            System.out.println("找到了下标为" + index);
//        }

        int[] arrValues = {1};
        int[] findValues = findFristAndLastSearch(arrValues, 1);
        System.out.println("找到了" + Arrays.toString(findValues));
    }

    /**
     *
     * @param arr 要查找 的数组
     * @param findVal 查找的值
     * @return  返回查找值在数组中的下标, 没有返回-1
     */
    public static int   seqSearch(int [] arr, int findVal) {
        for (int i = 0; i < arr.length; i++) {
            if (findVal == arr[i]) {
                return i;
            }
        }
        return -1;

    }

    public static int[] findFristAndLastSearch(int[]  arr, int findVal) {
        int[] findValues = new int[2];
        for (int i = 0; i < findValues.length; i++) {
            findValues[i] = -1;

        }
        int firstIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (findVal == arr[i]) {
                firstIndex = i;
                findValues[0] = i;
                // 找到第一个以后就直接break回去了
                break;
            }
        }
        System.out.println("ssss" + firstIndex);
        for (int i = arr.length - 1; i >=firstIndex; i--) {
            if (findVal == arr[i]) {
                findValues[1] = i;
                break;
            }
        }
        return findValues;
    }
}
