package com.mhz.datastructure.search;

/**
 * 插值查找
 * 原理:
 * 1. 插值查找算法类似于二分查找,
 * 不同的是插值查找每次从自适应mid处,开始查找
 * .
 * 2.将二分查找求mid索引的公式, left表示左边索引
 * right表示右边索引
 * key就是前面我们将的findValue
 * mid=(left+right)/2=left+(1/2)*(right-left)
 * 该成
 * mid=left+(findValue-arr[left])/(arr[right]-arr[left])*(right-left)
 * 3. 需要主要的是:插值查找,也是需要数组是有序的数组的
 */
public class InsertValueSearch {

    public static void main(String[] agrs) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = insertSearch(arr, 1234, 0, arr.length);
        System.out.println("找到值的下标为" + index);
    }


    public static int insertSearch(int[] arr, int findValue, int left, int right) {
        // 先让(right指针)最后的指针, 指向数组的最后的元素
        right = right - 1;
        if (left > right) {
            return -1;
        }
        // 如果要查找的值, 比最大值还大, 或者比最小值还小, 就直接返回没有找到.
        // 为什么二分查找里面没有这句话呢?
        // 我觉得二分查找里面加上这句话, 也不多, 也不可以不添加,
        // 但是插值查找里面, 一定要加上 ,因为插值查找里面的int mid是需要通过findValue来确定的,
        // 如果findValue值, 超过太多最大的值,或者最小值的时候 得到的mid很可能不在数组中, 会出现数组越界的 异常
        if (findValue > arr[right] || findValue < arr[left]) {
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {
            return insertSearch(arr, findValue, mid, right);
        } else if (findValue < midValue) {
            return insertSearch(arr, findValue, left, mid);
        } else {
            return mid;
        }

    }
}
