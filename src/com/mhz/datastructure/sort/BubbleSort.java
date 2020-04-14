package com.mhz.datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 说明:
 * 通过对待排序序列从前往后,(从下标较小的元素开始) 依次比较相邻元素的值,如果发现逆序则交换,
 * 使值较大的元素逐渐从前移向后部, 就像水底下的气泡一样逐渐向上冒.
 *
 * 优化: 因为排序的过程中, 各元素不断接近自己的位置, 如果一趟比较下来没有进行过交换, 就说明序列有序
 * 因此要在排序过程中设置一个flag 判断元素是否进行过交换, 从而减少不必要的比较
 * (这里说的优化, 可以在冒泡排序写好后, 在进行)
 */
public class BubbleSort {
    // 3 9 -1 10 20

    public static void main(String[] agrs) {
        int arr[] = {3, 9, -1, 10, 20};
        System.out.println("排序前数组====" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后数组====" + Arrays.toString(arr));


    }

    /**
     *  冒泡排序的算法
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 冒泡排序的时间复杂度 O(n^2)
        int temp=0; // 临时变量, 用于交换
        boolean flag = false; // flag 用于判断是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大, 就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    // 交换
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
            // 在一趟的排序中, 一次交换都没发生过
            if (!flag) {
                // 直接返回回去
                break;
            } else {
                // 重置flag进行下次判断
                flag = false;
            }

        }


    }
}
