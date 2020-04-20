package com.mhz.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 计数排序
 */
public class CountSort {


    public static void main(String[] args) {
//        int[] arr = {22, 34, 2, 49, 89, 20, 65, 32, 3};
        int[] arr = getRandomArray(80000000);
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        countSortFinally(arr);

        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = format2.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
//        System.out.println("排序后的数组为" + Arrays.toString(arr));
    }

    public static int[] getRandomArray(int maxSize) {
        int[] arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            // 随机生成[0,8000000]之间的数
            arr[i] = (int) (Math.random() * 8000000);
        }
        return arr;
    }


    /**
     * 计数排序优化
     */
    public static void countSortFinally(int[] arr) {
        int max = arr[0];// 最大值
        int min = arr[0]; // 最小值
        // 确定最大值, 最小值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 含头,不含尾   用于计数, 计算出来每个元素在,数组中出现的次数
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - min]++;
        }
        // 用于确定待排序元素的位置
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 从数据的后面开始遍历, 确定,排序数据元素的位置
        int[] temp = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            //确定元素在数组中的位置, 然后先放入到临时数组中, 正确的位置
            //每次先拿到counts[arr[i] - min]的值,然后减去1 ,就是当前元素,在数组中正确的位置
            temp[--counts[arr[i] - min]] = arr[i];
        }
        // 将临时的数组, 在放回到待排序的数组
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }



    }








    /**
     * 计数排序的简单实现
     * 这个 方法只能对正整数的数组 进行排序
     * 极其浪费内存空间（因为是只看到最大值, 没有看最小值）
     * 如果一个数组([100000,200000))就会造成空间的浪费
     * 是个不稳定的排序, 关于稳定, 请查看稳定说明.text
     */
    public static void countSortSimple(int[] arr) {
        // 1.首先确定数据中最大的值
        // 假定最大值为第一个元素
        int max = arr[0];
        // 从第二个元素开始遍历比较最大值,求出最大值
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // 统计元素出现的次数
        int[] counts = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;

        }
        // 按照顺序排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- > 0) {
//                arr[index] = i;
//                index++;
                arr[index++] = i;
            }
        }

    }
}
