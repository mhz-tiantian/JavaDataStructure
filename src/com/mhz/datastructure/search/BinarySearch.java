package com.mhz.datastructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 请对一个有序数组进行二分查找{1,8,10,89,1000,1234}
 * .输入一个数看看该数组是否存在次数, 并且求出下标
 * 如果没有就提示"没有这个数"
 * <p>
 * 二分查找的思路:
 * 1. 首先确定该数组的中间的下标
 * mid=(left+right)/2
 * 2.然后让需要查找的数(findvalue) 和arr[mid]比较
 * 2.1 如果findvalue>arr[mid]说明你要查找的数在mid的右边, 因此
 * 说明我们需要向右查找
 * 2.2 如果findValue<arr[mid]说明你要查找的数在mid的左边,
 * 因此,说明我们需要向左查找
 * 2.3 如果findValue==arr[mid]说明我们要查找的数, 刚好是我们需要找的值
 * 就返回
 * <p>
 * 3. 什么时候结束递归
 * 3.1找到结束递归
 * 3.2如果left>right的时候,还没有找到,说明我们查找的数, 不存在当前的数组中
 * 也应该也结束递归
 */
// 注意: 最重要的一点, 查找的数组必须是有序的, 如果没有序的话, 会出现
// java.lang.StackOverflowError 这个异常, 就是出现死递归的方法, 出现了
public class BinarySearch {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        int[] arr = {1, 8, 10, 1000, 1000, 1001, 1234};

//        int index = binarySearch(arr, 1234, 0, arr.length);
//        System.out.println("查找到的下标为" + index);


        List<Integer> indexs = binarySearch2(arr, 1000, 0, arr.length);
        System.out.println("查找到的下标为" + indexs.toString());
    }

    /**
     * 二分查找， 只返回一個下标的方法
     *
     * @param arr       有序的数组
     * @param findValue 需要查找的值
     * @param left      左边的下标
     * @param right     右边的下标
     * @return 返回查找值, 在该有序数组的下边, 如果没有返回-1
     */
    private static int binarySearch(int[] arr, int findValue, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        // 向右递归. 查找
        if (findValue > midValue) {
            return binarySearch(arr, findValue, mid, right);
        } else if (findValue < midValue) {
            // 向左递归, 查找
            return binarySearch(arr, findValue, left, mid);
        } else {
            // 找到了, 直接返回
            return mid;
        }


    }

    // 思考题

    /**
     * 课后思考题:{1,8,10,89,1000,1000,1234}
     * 当有一个有序数组中,有多个相同的数值时,如何将所有的数值的下标
     * 都查找到,比如这里的1000
     * <p>
     * 思路分析:
     * 在找到mid索引值,不要马上返回
     * 2.向mid索引值的左边扫描,将所有满足1000 的元素的下标,加入到集合ArrayList
     * 3.向mid 索引值的右扫描,将所有满足1000的元素的下标,加入到集合ArrayList中
     * 4. 将ArrayList返回
     */

    public static List<Integer> binarySearch2(int[] arr, int findValue, int left, int right) {
        // 説明递归整个数组, 还是没有找到需要查找的值(findValue)
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return binarySearch2(arr, findValue, mid, right);
        } else if (findValue < midValue) {
            return binarySearch2(arr, findValue, left, mid);
        } else {
            List<Integer> indexArrayList = new ArrayList<>();
            // 把中间相等于的值, 加到集合里面去
            indexArrayList.add(mid);
            int index = mid;
            while (index < arr.length - 1) {
                //因为这里的++index所以, 需要小于arr.length-1
                if (findValue == arr[++index]) {
                    indexArrayList.add(index);
                }
            }
            // 重新把指针, 移动到中间的问题, 向左边查找与findValue值相等于的值
            index = mid;

            while (index > 0) {
                if (findValue == arr[--index]) {
                    indexArrayList.add(index);
                }
            }
            return indexArrayList;
        }
    }


}
